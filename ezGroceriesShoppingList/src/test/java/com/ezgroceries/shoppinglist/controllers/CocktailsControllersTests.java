package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.model.CocktailDTO;
import com.ezgroceries.shoppinglist.services.CocktailsService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = CocktailsController.class)
public class CocktailsControllersTests {
    @MockBean
    private CocktailsService cocktailsService;

    @Autowired
    private MockMvc mockMvc;

    UUID uuid;
    CocktailDTO cocktail;
    ArrayList<CocktailDTO> cocktailArray = new ArrayList<>();

    @BeforeEach
    public void init() {
        uuid = UUID.randomUUID();
        cocktail = new CocktailDTO(
            uuid.toString(),
            "Name",
            "glass",
            "instructions",
            "image",
            new ArrayList<>() {{
                add("ingredient 1");
                add("ingredient 2");
            }}
        );
        cocktailArray.add(cocktail);
    }

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }

    @Test
    void shouldReturnListOfCocktails() throws Exception {
        when(cocktailsService.getList()).thenReturn(cocktailArray);
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/cocktails"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Name"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].glass").value("glass"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].instructions").value("instructions"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].image").value("image"));
    }

    @Test
    void shouldCreateCocktail() throws Exception {
        this.mockMvc
            .perform(MockMvcRequestBuilders
                .post("/cocktails/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "    \"name\": \"Margerita\",\n" +
                    "    \"glass\": \"Cocktail glass\",\n" +
                    "    \"instructions\": \"Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..\",\n" +
                    "    \"image\": \"https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg\",\n" +
                    "    \"ingredients\": [\n" +
                    "        \"Tequila\",\n" +
                    "        \"Triple sec\",\n" +
                    "        \"Lime juice\",\n" +
                    "        \"Salt\"\n" +
                    "    ]\n" +
                    "}")
            )
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.header().exists("Location"));
    }

    @Test
    void shouldReturnCocktail() throws Exception {
        cocktailsService.addItem(cocktail);
        when(cocktailsService.getItem(uuid.toString())).thenReturn(cocktail);
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/cocktails/" + uuid.toString()))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Name"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].glass").value("glass"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].instructions").value("instructions"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].image").value("image"));
    }
}
