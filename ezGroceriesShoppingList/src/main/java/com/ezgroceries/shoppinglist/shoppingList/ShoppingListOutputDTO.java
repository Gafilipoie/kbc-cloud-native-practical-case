package com.ezgroceries.shoppinglist.shoppingList;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class ShoppingListOutputDTO {

    private String shoppingListId;
    private String name;
    private Set<String> ingredients;

    public ShoppingListOutputDTO(String shoppingListId, String name, Set<String> ingredients) {
        this.shoppingListId = shoppingListId;
        this.name = name;
        this.ingredients = ingredients;
    }

}