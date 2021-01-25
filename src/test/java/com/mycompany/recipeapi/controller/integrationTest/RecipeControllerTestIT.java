package com.mycompany.recipeapi.controller.integrationTest;

import com.mycompany.recipeapi.controller.RecipeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RecipeController.class)
public class RecipeControllerTestIT {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllRecipesAPI() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .get("/recipes")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.recipes").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.recipes[*].id").isNotEmpty());
    }
}
