package com.ezgroceries.shoppinglist.controller;

import com.ezgroceries.shoppinglist.client.CocktailDBClient;
import com.ezgroceries.shoppinglist.model.CocktailDBResponse;
import com.ezgroceries.shoppinglist.model.CocktailOutputDTO;
import com.ezgroceries.shoppinglist.service.CocktailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CocktailController {
    private final static Logger log = LoggerFactory.getLogger(CocktailController.class);

    private final CocktailDBClient cocktailDBClient;
    private final CocktailService cocktailService;

    @GetMapping("/cocktails")
    public ResponseEntity<List<CocktailOutputDTO>> getCocktails(@RequestParam String search) {
        log.info("Getting list of all cocktails containing : {}", search);
        CocktailDBResponse cocktailDBResponse = cocktailDBClient.searchCocktails(search);
        return ResponseEntity.ok(cocktailService.mergeCocktails(cocktailDBResponse.getDrinks()));
    }
}
