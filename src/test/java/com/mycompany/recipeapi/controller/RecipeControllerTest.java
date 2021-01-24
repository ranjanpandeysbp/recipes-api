package com.mycompany.recipeapi.controller;

import com.mycompany.recipeapi.entity.RecipeEntity;
import com.mycompany.recipeapi.entity.UserEntity;
import com.mycompany.recipeapi.exception.ResourceNotFoundException;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class RecipeControllerTest {

    @InjectMocks
    private RecipeController recipeController;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private UserRepository userRepository;

    List<RecipeEntity> recipeEntityList = null;
    RecipeEntity recipeEntity = null;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        recipeEntityList = new ArrayList<>();

        recipeEntity = new RecipeEntity();
        recipeEntity.setRecipeName("Chole Bhature");
        recipeEntity.setDishType("Vegetarian");
        recipeEntityList.add(recipeEntity);

        recipeEntity = new RecipeEntity();
        recipeEntity.setRecipeName("Kadhai Paneer");
        recipeEntity.setDishType("Vegetarian");
        recipeEntityList.add(recipeEntity);
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

    @Test(expected = ResourceNotFoundException.class)
    public void test_create_recipe_userNotFound(){

        UserEntity userEntity = new UserEntity();
        userEntity.setName("test");
        userEntity.setEmail("test@gmail.com");

        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setDishType("Veg");
        recipeEntity.setRecipeName("Veg Biryani");
        recipeEntity.setUserEntity(userEntity);

        Mockito.when(userRepository.findById(25L)).thenReturn(Optional.empty());
        Mockito.when(recipeRepository.save(recipeEntity)).thenReturn(recipeEntity);

        ResponseEntity<RecipeEntity> re = recipeController.createRecipe(recipeEntity, 1L);
    }

}
