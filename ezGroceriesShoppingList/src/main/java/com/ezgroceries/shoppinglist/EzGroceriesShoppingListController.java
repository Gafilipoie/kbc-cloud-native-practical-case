package com.ezgroceries.shoppinglist;

import com.ezgroceries.shoppinglist.model.Cocktail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
public class EzGroceriesShoppingListController {
    private final static Logger logger = LoggerFactory.getLogger(EzGroceriesShoppingListController.class);

    @RequestMapping("/cocktails")
    public ArrayList<Cocktail> helloWorld(@RequestParam(required = false) String search) {
        logger.info("User requested cocktails");
        return findCocktails(search);
    }

    private ArrayList<Cocktail> findCocktails(String search) {
        return new ArrayList<Cocktail>() {{
            add(new Cocktail(
                "23b3d85a-3928-41c0-a533-6538a71e17c4",
                "Margerita",
                "Cocktail glass",
                "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
                "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",
                new ArrayList<String>() {{
                    add("Tequila");
                    add("Triple sec");
                    add("Lime juice");
                    add("Salt");
                }}
            ));
            add(new Cocktail(
                    "d615ec78-fe93-467b-8d26-5d26d8eab073",
                    "Blue Margerita",
                    "Cocktail glass",
                    "Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..",
                    "https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg",
                    new ArrayList<String>() {{
                        add("Tequila");
                        add("Blue Curacao");
                        add("Lime juice");
                        add("Salt");
                    }}
            ));
        }};
    }
}
