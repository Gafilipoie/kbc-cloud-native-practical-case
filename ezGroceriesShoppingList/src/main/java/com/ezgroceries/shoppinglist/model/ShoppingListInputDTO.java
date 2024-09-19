package com.ezgroceries.shoppinglist.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShoppingListInputDTO {

    private String name;

    public ShoppingListInputDTO(String name) {
        this.name = name;
    }

}