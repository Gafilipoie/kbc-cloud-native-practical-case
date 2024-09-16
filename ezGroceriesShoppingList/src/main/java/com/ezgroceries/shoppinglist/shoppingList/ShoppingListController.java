package com.ezgroceries.shoppinglist.shoppingList;

import com.ezgroceries.shoppinglist.EzGroceriesShoppingListApplication;
import com.ezgroceries.shoppinglist.cocktail.CocktailController;
//import com.ezgroceries.shoppinglist.cocktail.CocktailDTO;
//import com.ezgroceries.shoppinglist.cocktail.CocktailIdDTO;
import com.ezgroceries.shoppinglist.cocktail.CocktailService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.UUID;

@RestController
public class ShoppingListController {
    private final static Logger logger = LoggerFactory.getLogger(CocktailController.class);

    @Autowired
    ShoppingListService shoppingListService;

    @Autowired
    CocktailService cocktailsService;

    @GetMapping("/shopping-list")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ArrayList<ShoppingListDTO>> getShoppingLists() {
        logger.info("<------ User requested shopping-lists ------>");
        return new ResponseEntity<>(shoppingListService.getList(), HttpStatus.OK);
    }

    @GetMapping("/shopping-list/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ShoppingListDTO> getShoppingList(@PathVariable String id) {
        if (shoppingListService.hasItem(id)) {
            logger.info("<------ User requested Shopping List ------>");
            final ShoppingListDTO shoppingList = shoppingListService.getItem(id);
            return new ResponseEntity<>(shoppingList, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/shopping-list/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ShoppingListDTO> createShoppingList(@RequestBody ShoppingListDTO shoppingList, HttpServletRequest request) {
        logger.info("<------ User created Shopping List ------>");
        shoppingList.setId(UUID.randomUUID().toString());
        shoppingListService.addItem(shoppingList);
        URI location = ServletUriComponentsBuilder.fromRequestUri(request)
            .path("/{id}")
            .buildAndExpand(shoppingList.getId())
            .toUri();
        return ResponseEntity.created(location).body(shoppingList);
    }

//    @PatchMapping(value = "/shopping-list/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    public ResponseEntity<String> updateShoppingList(@PathVariable String id, @RequestBody CocktailIdDTO cocktailId) {
//        if (shoppingListService.hasItem(id)) {
//            logger.info("<------ User updated Shopping List ------>");
//            CocktailDTO cocktail = cocktailsService.getItem(cocktailId.getCocktailId());
//            shoppingListService.updateItem(id, cocktail);
//            return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
//    }

    @DeleteMapping(value = "/shopping-list/delete/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<String> createCocktails(@PathVariable String id) {
        if (shoppingListService.hasItem(id)) {
            logger.info("<------ User deleted Shopping List " + id + " ------>");
            shoppingListService.removeItem(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }
}
