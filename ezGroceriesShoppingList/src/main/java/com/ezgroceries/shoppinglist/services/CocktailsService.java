package com.ezgroceries.shoppinglist.services;

import com.ezgroceries.shoppinglist.model.CocktailDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CocktailsService {
    private final ArrayList<CocktailDTO> cocktailsList = new ArrayList<>();

    public ArrayList<CocktailDTO> getList() {
        return cocktailsList;
    }

    public CocktailDTO getItem(String id) {
        return cocktailsList.stream()
            .filter(e -> e.getId().equals(id))
            .findAny()
            .orElse(null);
    }

    public boolean hasItem(String id) {
        return cocktailsList.stream().anyMatch(o -> o.getId().equals(id));
    }

    public void addItem(CocktailDTO item) {
        cocktailsList.add(item);
    }

    public void updateItem(String id, CocktailDTO newItem) {
        CocktailDTO cocktail = cocktailsList.stream()
                .filter(e -> e.getId().equals(id))
                .findAny()
                .orElse(null);

        if (cocktail != null) {
            int position = cocktailsList.indexOf(cocktail);
            cocktailsList.set(position, newItem);
        }

    }

    public void removeItem(String id) {
        cocktailsList.removeIf(e -> e.getId().equals(id));
    }
}

