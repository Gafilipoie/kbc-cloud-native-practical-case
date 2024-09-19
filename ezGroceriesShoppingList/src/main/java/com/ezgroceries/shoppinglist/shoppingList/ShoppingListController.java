package com.ezgroceries.shoppinglist.shoppingList;

import com.ezgroceries.shoppinglist.cocktail.CocktailDBClient;
import com.ezgroceries.shoppinglist.cocktail.CocktailInputDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShoppingListController {
    private final static Logger log = LoggerFactory.getLogger(ShoppingListController.class);

    private final CocktailDBClient cocktailDBClient;
    private final ShoppingListService shoppingListService;

    @GetMapping("/shopping-lists")
    public ResponseEntity<List<ShoppingListOutputDTO>> getAllShoppingLists() {
        log.info("Get all shopping lists");
        return ResponseEntity.ok(shoppingListService.getAll());
    }

    @GetMapping("/shopping-lists/{shoppingListId}")
    public ResponseEntity<ShoppingListOutputDTO> getShoppingList(@PathVariable String shoppingListId) {
        log.info("Get shopping list with id : {}", shoppingListId);
        return ResponseEntity.ok(shoppingListService.getById(shoppingListId));
    }

    @PostMapping("/shopping-lists")
    public ResponseEntity<?> postShoppingLists(@RequestBody ShoppingListInputDTO shoppingListInputDTO) {
        log.info("Start creation of shopping list with name : {}", shoppingListInputDTO.getName());

        ShoppingListEntity shoppingListEntity = shoppingListService.create(shoppingListInputDTO.getName());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{shoppingListId}")
                .buildAndExpand(shoppingListEntity.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/shopping-lists/{shoppingListId}/cocktails")
    public ResponseEntity<?> postCocktailToShoppingList(@PathVariable String shoppingListId, @RequestBody CocktailInputDTO cocktailInputDTO) {
        log.info("Adding cocktail with id : {} to shoppingList with id : {} ", cocktailInputDTO.getCocktailId(), shoppingListId);

        shoppingListService.addCocktail(shoppingListId, cocktailInputDTO.getCocktailId());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{cocktailId}")
                .buildAndExpand(cocktailInputDTO.getCocktailId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
