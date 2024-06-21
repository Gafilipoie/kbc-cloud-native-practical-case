package com.ezgroceries.shoppinglist.model;

import java.util.ArrayList;

public class ShoppingListDTO {
    String id;
    String name;

    ArrayList<String> ingredients = new ArrayList<>();

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return this.name;
    }

    public ArrayList<String> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        for (String ingredient : ingredients) {
            if (this.ingredients.contains(ingredient)) continue;
            this.ingredients.add(ingredient);
        }
    }
}
