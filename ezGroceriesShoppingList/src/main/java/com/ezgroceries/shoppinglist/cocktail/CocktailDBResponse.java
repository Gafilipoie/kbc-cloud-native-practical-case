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
        private String strIngredient1;
        private String strIngredient2;
        private String strIngredient3;
        private String strIngredient4;

        public DrinkResource() {
        }

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
            this.strIngredient1 = strIngredient1;
            this.strIngredient2 = strIngredient2;
            this.strIngredient3 = strIngredient3;
            this.strIngredient4 = strIngredient4;
        }
    }
}