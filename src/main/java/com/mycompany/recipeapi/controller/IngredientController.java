package com.mycompany.recipeapi.controller;

import com.mycompany.recipeapi.entity.IngredientEntity;
import com.mycompany.recipeapi.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping("/ingredients")
    public Iterable<IngredientEntity> getAllIngredients(){
        return ingredientRepository.findAll();
    }
}
