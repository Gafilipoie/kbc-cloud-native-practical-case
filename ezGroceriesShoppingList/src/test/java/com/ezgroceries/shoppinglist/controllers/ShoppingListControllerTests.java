package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.model.ShoppingListDTO;
import com.ezgroceries.shoppinglist.services.CocktailsService;
import com.ezgroceries.shoppinglist.services.ShoppingListService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = ShoppingListController.class)
public class ShoppingListControllerTests {
    @MockBean
    private ShoppingListService shoppingListService;

    @MockBean
    private CocktailsService cocktailsService;

    @Autowired
    private MockMvc mockMvc;

    ShoppingListDTO shoppingList = new ShoppingListDTO();
    ArrayList<ShoppingListDTO> shoppingListArray = new ArrayList<>();

    @BeforeEach
    public void init() {
        shoppingList.setId(UUID.randomUUID().toString());
        shoppingList.setName("Name");
        shoppingListArray.add(shoppingList);
    }

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }

    @Test
    void shouldReturnListOfCocktails() throws Exception {
        when(shoppingListService.getList()).thenReturn(shoppingListArray);
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/shopping-list"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Name"));
    }
}
