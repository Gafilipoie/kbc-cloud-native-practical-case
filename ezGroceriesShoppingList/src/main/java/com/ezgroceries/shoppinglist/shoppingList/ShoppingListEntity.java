package com.ezgroceries.shoppinglist.shoppingList;

import com.ezgroceries.shoppinglist.cocktail.CocktailEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "shopping_list")
public class ShoppingListEntity {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column
    private String name;

    @ManyToMany
    @JoinTable(
            name = "cocktail_shopping_list",
            joinColumns = @JoinColumn(name = "shopping_list_id"),
            inverseJoinColumns = @JoinColumn(name = "cocktail_id"))
    Set<CocktailEntity> cocktails;

    public ShoppingListEntity() {}

    public ShoppingListEntity(String name) {
        this.name = name;
    }

    public void addCocktail(CocktailEntity cocktailEntity) {
        if (this.cocktails == null) {
            this.cocktails = new HashSet<>();
        }
        this.cocktails.add(cocktailEntity);
    }
}
