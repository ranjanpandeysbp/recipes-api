package com.mycompany.recipeapi.controller;

import com.mycompany.recipeapi.entity.IngredientEntity;
import com.mycompany.recipeapi.repository.IngredientRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class IngredientControllerTest {

    @InjectMocks
    private IngredientController ingredientController;
    @Mock
    private IngredientRepository ingredientRepository;

    List<IngredientEntity> tempIngredientList = null;

    @Before
    public void init(){
        tempIngredientList = Arrays.asList(
                new IngredientEntity("Basmati Rice", "A fragrant, long grain rice cultivated in India"),
                new IngredientEntity("Chickpeas", "A high-protein, high-fiber legume that is a staple in Indian cuisine")
        );
    }

    @Test
    public void test_get_all_ingredients_whenFound(){

        Mockito.when(ingredientRepository.findAll()).thenReturn(tempIngredientList);

        List<IngredientEntity> ingredientList = (List<IngredientEntity>) ingredientController.getAllIngredients();
        Assert.assertEquals(2, ingredientList.size());
    }

    @Test
    public void test_empty_ingredients_whenNotFound(){

        Mockito.when(ingredientRepository.findAll()).thenReturn(new ArrayList<>());

        List<IngredientEntity> ingredientList = (List<IngredientEntity>) ingredientController.getAllIngredients();
        Assert.assertEquals(0, ingredientList.size());
    }
}
