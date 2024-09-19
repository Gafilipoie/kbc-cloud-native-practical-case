package com.ezgroceries.shoppinglist.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CocktailOutputDTO {

    private String cocktailId;
    private String name;
    private String glass;
    private String instructions;
    private String image;
    private Set<String> ingredients;

    public CocktailOutputDTO() {
    }

    public CocktailOutputDTO(String cocktailId, String name, String glass, String instructions, String image, Set<String> ingredients) {
        this.cocktailId = cocktailId;
        this.name = name;
        this.glass = glass;
        this.instructions = instructions;
        this.image = image;
        this.ingredients = ingredients;
    }
}