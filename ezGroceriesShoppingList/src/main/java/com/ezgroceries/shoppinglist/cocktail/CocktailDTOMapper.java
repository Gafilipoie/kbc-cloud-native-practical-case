package com.ezgroceries.shoppinglist.cocktail;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CocktailDTOMapper implements Function<CocktailDBResponse.DrinkResource, CocktailDTO> {
    @Override
    public CocktailDTO apply(CocktailDBResponse.DrinkResource cocktail) {
        return new CocktailDTO(
                cocktail.getIdDrink(),
                cocktail.getStrDrink(),
                cocktail.getStrGlass(),
                cocktail.getStrInstructions(),
                cocktail.getStrDrinkThumb(),
                cocktail.getIngredients()
        );
    }
}
