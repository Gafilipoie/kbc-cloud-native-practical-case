package com.ezgroceries.shoppinglist.services;

import com.ezgroceries.shoppinglist.model.CocktailDTO;
import com.ezgroceries.shoppinglist.model.ShoppingListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ShoppingListService {
    @Autowired
    CocktailsService cocktailsService;

    private final ArrayList<ShoppingListDTO> shoppingList = new ArrayList<>();

    public ArrayList<ShoppingListDTO> getList() {
        return shoppingList;
    }

    public ShoppingListDTO getItem(String id) {
        return shoppingList.stream()
            .filter(e -> e.getId().equals(id))
            .findAny()
            .orElse(null);
    }

    public boolean hasItem(String id) {
        return shoppingList.stream().anyMatch(o -> o.getId().equals(id));
    }

    public void addItem(ShoppingListDTO item) {
        shoppingList.add(item);
    }

    public void updateItem(String id, CocktailDTO cocktail) {
        ArrayList<String> ingredients = cocktail.getIngredients();

        ShoppingListDTO shoppingItem = shoppingList.stream()
                .filter(e -> e.getId().equals(id))
                .findAny()
                .orElse(null);

        if (shoppingItem != null) {
            int position = shoppingList.indexOf(shoppingItem);
            shoppingItem.setIngredients(ingredients);
            shoppingList.set(position, shoppingItem);
        }
    }

    public void removeItem(String id) {
        shoppingList.removeIf(e -> e.getId().equals(id));
    }
}
