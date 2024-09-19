package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.client.CocktailDBClient;
import com.ezgroceries.shoppinglist.entity.CocktailEntity;
import com.ezgroceries.shoppinglist.model.CocktailDBResponse;
import com.ezgroceries.shoppinglist.model.CocktailOutputDTO;
import com.ezgroceries.shoppinglist.repository.CocktailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CocktailService {

    private final CocktailRepository cocktailRepository;
    private final CocktailDBClient cocktailDBClient;

    public List<CocktailOutputDTO> getList(String search) {
        CocktailDBResponse cocktailDBResponse = cocktailDBClient.searchCocktails(search);
        return mergeCocktails(cocktailDBResponse.getDrinks());
    }

    public List<CocktailOutputDTO> mergeCocktails(List<CocktailDBResponse.DrinkResource> drinks) {
        //Get all the idDrink attributes
        List<String> ids = drinks
                .stream()
                .map(CocktailDBResponse.DrinkResource::getIdDrink)
                .collect(Collectors.toList());

        //Get all the ones we already have from our DB, use a Map for convenient lookup
        Map<String, CocktailEntity> existingEntityMap = cocktailRepository
                .findByIdDrinkIn(ids)
                .stream()
                .collect(Collectors.toMap(CocktailEntity::getIdDrink, o -> o, (o, o2) -> o));

        //Stream over all the drinks, map them to the existing ones, persist a new one if not existing
        Map<String, CocktailEntity> allEntityMap = drinks
                .stream()
                .map(drinkResource -> {
                    CocktailEntity cocktailEntity = existingEntityMap.get(drinkResource.getIdDrink());
                    if (cocktailEntity == null) {
                        CocktailEntity newCocktailEntity = new CocktailEntity();
                        newCocktailEntity.setId(UUID.randomUUID());
                        newCocktailEntity.setIdDrink(drinkResource.getIdDrink());
                        newCocktailEntity.setName(drinkResource.getStrDrink());
                        newCocktailEntity.setGlass(drinkResource.getStrGlass());
                        newCocktailEntity.setInstructions(drinkResource.getStrInstructions());
                        newCocktailEntity.setImageLink(drinkResource.getStrDrinkThumb());
                        newCocktailEntity.setIngredients(getIngredients(drinkResource));
                        cocktailEntity = cocktailRepository.save(newCocktailEntity);
                    }
                    return cocktailEntity;
                })
                .collect(Collectors.toMap(CocktailEntity::getIdDrink,o -> o, (o,o2) -> o));

        //Merge drinks and our entities, transform to CocktailResource instances
        return mergeAndTransform(drinks, allEntityMap);
    }

    private List<CocktailOutputDTO> mergeAndTransform(
            List<CocktailDBResponse.DrinkResource> drinks,
            Map<String, CocktailEntity> allEntityMap
    ) {
        return drinks
                .stream()
                .map(drinkResource -> new CocktailOutputDTO(
                        allEntityMap.get(drinkResource.getIdDrink()).getId().toString(),
                        drinkResource.getStrDrink(),
                        drinkResource.getStrGlass(),
                        drinkResource.getStrInstructions(),
                        drinkResource.getStrDrinkThumb(),
                        getIngredients(drinkResource)
                ))
                .collect(Collectors.toList());
    }

    private Set<String> getIngredients(CocktailDBResponse.DrinkResource drinkResource) {
        return Stream.of(drinkResource.getStrIngredient1(),
                        drinkResource.getStrIngredient2(),
                        drinkResource.getStrIngredient3(),
                        drinkResource.getStrIngredient4())
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}
