package com.mycompany.recipeapi.controller;

import com.mycompany.recipeapi.entity.RecipeEntity;
import com.mycompany.recipeapi.entity.UserEntity;
import com.mycompany.recipeapi.repository.RecipeRepository;
import com.mycompany.recipeapi.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class RecipeControllerTest {

    @InjectMocks
    private RecipeController recipeController;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private UserRepository userRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_create_recipe(){

        UserEntity userEntity = new UserEntity();
        userEntity.setName("Admin");
        userEntity.setEmail("admin@gmail.com");

        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setDishType("Veg");
        recipeEntity.setRecipeName("Veg Biryani");
        recipeEntity.setUserEntity(userEntity);

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        Mockito.when(recipeRepository.save(recipeEntity)).thenReturn(recipeEntity);

        ResponseEntity<RecipeEntity> re = recipeController.createRecipe(recipeEntity, 1L);
        Assert.assertEquals(201, re.getStatusCodeValue());
    }
}
