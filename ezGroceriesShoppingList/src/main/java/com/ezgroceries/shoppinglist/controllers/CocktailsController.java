package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.model.CocktailDTO;
import com.ezgroceries.shoppinglist.services.CocktailsService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.UUID;


@RestController
public class CocktailsController {
    private final static Logger logger = LoggerFactory.getLogger(CocktailsController.class);

    private final CocktailsService cocktailsService;

    public CocktailsController(CocktailsService cocktailsService) {
        this.cocktailsService = cocktailsService;
    }

    @GetMapping("/cocktails")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ArrayList<CocktailDTO>> getCocktails(@RequestParam(required = false) String search) {
        logger.info("Getting list of all cocktails containing : {}", search);
        return new ResponseEntity<>(cocktailsService.getList(), HttpStatus.OK);
    }

    @GetMapping("/cocktails/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<CocktailDTO> getCocktail(@PathVariable String id) {
        if (cocktailsService.hasItem(id)) {
            logger.info("<------ User requested cocktail ------>");
            final CocktailDTO cocktail = cocktailsService.getItem(id);
            return new ResponseEntity<>(cocktail, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/cocktails/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<CocktailDTO> createCocktail(@RequestBody CocktailDTO cocktail, HttpServletRequest request) {
        logger.info("<------ User created Cocktail ------>");
        cocktail.setId(UUID.randomUUID().toString());
        cocktailsService.addItem(cocktail);
        URI location = ServletUriComponentsBuilder.fromRequestUri(request)
            .path("/{id}")
            .buildAndExpand(cocktail.getId())
            .toUri();
        return ResponseEntity.created(location).body(cocktail);
    }

    @PutMapping(value = "/cocktails/update/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<String> updateCocktail(@PathVariable String id, @RequestBody CocktailDTO updatedCocktail) {
        if (cocktailsService.hasItem(id)) {
            logger.info("<------ User updated Cocktail " + id + " ------>");
            cocktailsService.updateItem(id, updatedCocktail);
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/cocktails/delete/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<String> createCocktails(@PathVariable String id) {
        if (cocktailsService.hasItem(id)) {
            logger.info("<------ User deleted Cocktail " + id + " ------>");
            cocktailsService.removeItem(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }
}
