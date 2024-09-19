package com.ezgroceries.shoppinglist.entity;

import com.ezgroceries.shoppinglist.util.StringSetConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "cocktail")
public class CocktailEntity {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column
    private String idDrink;

    @Column
    private String name;

    @Column
    private String glass;

    @Column
    private String instructions;

    @Column
    private String imageLink;

    @Column
    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;

    @ManyToMany(mappedBy = "cocktails")
    Set<ShoppingListEntity> shoppingLists;

    public CocktailEntity() {}
}
