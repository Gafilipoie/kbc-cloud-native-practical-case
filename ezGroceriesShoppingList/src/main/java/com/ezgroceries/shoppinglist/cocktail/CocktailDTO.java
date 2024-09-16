package com.ezgroceries.shoppinglist.cocktail;

import java.util.List;

public record CocktailDTO(
        String cocktailId,
        String name,
        String glass,
        String instructions,
        String image,
        List<String> ingredients
) {
}
