package com.mycompany.recipeapi.controller;

import com.mycompany.recipeapi.entity.RecipeEntity;
import com.mycompany.recipeapi.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("/recipes")
    public ResponseEntity<List<RecipeEntity>> getAllRecipes(){
        List<RecipeEntity> recipes = (List<RecipeEntity>)recipeRepository.findAll();
        return new ResponseEntity<List<RecipeEntity>>(recipes, HttpStatus.OK);
    }

    @PostMapping("/recipes")
    public ResponseEntity<RecipeEntity> createRecipe(@RequestBody RecipeEntity recipe){
        RecipeEntity recipeEntity = recipeRepository.save(recipe);
        return new ResponseEntity<RecipeEntity>(recipeEntity, HttpStatus.CREATED);
    }

    @PutMapping("/recipes")
    public ResponseEntity<RecipeEntity> updateRecipe(@RequestBody RecipeEntity recipeEntity){

        Optional<RecipeEntity> recipeEntityOpt = recipeRepository.findById(recipeEntity.getId());
        if(recipeEntityOpt.isPresent()){
            if (null != recipeEntity.getRecipeName()){
                recipeEntityOpt.get().setRecipeName(recipeEntity.getRecipeName());
            }
            if (null != recipeEntity.getDishType()){
                recipeEntityOpt.get().setDishType(recipeEntity.getDishType());
            }
            if (null != recipeEntity.getCookingInstruction()){
                recipeEntityOpt.get().setCookingInstruction(recipeEntity.getCookingInstruction());
            }
            if (null != recipeEntity.getNoOfPeople()){
                recipeEntityOpt.get().setNoOfPeople(recipeEntity.getNoOfPeople());
            }
            if(!recipeEntity.getIngredientEntityList().isEmpty()){
                recipeEntityOpt.get().setIngredientEntityList(recipeEntity.getIngredientEntityList());
            }
            return new ResponseEntity<>(recipeRepository.save(recipeEntityOpt.get()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/recipes/{id}")
    public ResponseEntity<Long> deleteRecipe(@PathVariable("id") Long id){
        Optional<RecipeEntity> recipeEntityOpt = recipeRepository.findById(id);
        if(recipeEntityOpt.isPresent()){
            recipeRepository.delete(recipeEntityOpt.get());
            return new ResponseEntity<Long>(id, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Long>(id, HttpStatus.NOT_FOUND);
    }
}
