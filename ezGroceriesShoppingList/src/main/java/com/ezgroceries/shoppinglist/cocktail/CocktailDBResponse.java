package com.ezgroceries.shoppinglist.cocktail;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
public class CocktailDBResponse {
    private ArrayList<DrinkResource> drinks;

    @Setter
    @Getter
    public static class DrinkResource {
        private String idDrink;
        private String strDrink;
        private String strGlass;
        private String strInstructions;
        private String strDrinkThumb;
        private ArrayList<String> ingredients = new ArrayList<>();

        public DrinkResource(
                String idDrink,
                String strDrink,
                String strGlass,
                String strInstructions,
                String strDrinkThumb,
                String strIngredient1,
                String strIngredient2,
                String strIngredient3,
                String strIngredient4
        ) {
            this.idDrink = idDrink;
            this.strDrink = strDrink;
            this.strGlass = strGlass;
            this.strInstructions = strInstructions;
            this.strDrinkThumb = strDrinkThumb;
            this.ingredients.add(strIngredient1);
            this.ingredients.add(strIngredient2);
            if (strIngredient3 != null) this.ingredients.add(strIngredient3);
            if (strIngredient4 != null) this.ingredients.add(strIngredient4);
        }
    }
}