package com.ezgroceries.shoppinglist.cocktail;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CocktailService {
//    private final ArrayList<CocktailDTO> cocktailsList = new ArrayList<>();
    private final CocktailDBClient cocktailDBClient;
    private final CocktailDTOMapper cocktailDTOMapper;

    public List<CocktailDTO> getList(String search) {
        CocktailDBResponse cocktailDBResponse = cocktailDBClient.searchCocktails(search);
        return cocktailDBResponse
                .getDrinks()
                .stream()
                .map(cocktailDTOMapper)
                .collect(Collectors.toList());
    }

//    public CocktailDTO getItem(String id) {
//        return cocktailsList.stream()
//            .filter(e -> e.getId().equals(id))
//            .findAny()
//            .orElse(null);
//    }
//
//    public boolean hasItem(String id) {
//        return cocktailsList.stream().anyMatch(o -> o.getId().equals(id));
//    }
//
//    public void addItem(CocktailDTO item) {
//        cocktailsList.add(item);
//    }
//
//    public void updateItem(String id, CocktailDTO newItem) {
//        CocktailDTO cocktail = cocktailsList.stream()
//                .filter(e -> e.getId().equals(id))
//                .findAny()
//                .orElse(null);
//
//        if (cocktail != null) {
//            int position = cocktailsList.indexOf(cocktail);
//            cocktailsList.set(position, newItem);
//        }
//
//    }
//
//    public void removeItem(String id) {
//        cocktailsList.removeIf(e -> e.getId().equals(id));
//    }
}

