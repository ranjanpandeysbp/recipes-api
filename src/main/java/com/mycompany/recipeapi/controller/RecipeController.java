package com.mycompany.recipeapi.controller;

import com.mycompany.recipeapi.entity.RecipeEntity;
import com.mycompany.recipeapi.entity.UserEntity;
import com.mycompany.recipeapi.exception.ResourceNotFoundException;
import com.mycompany.recipeapi.repository.RecipeRepository;
import com.mycompany.recipeapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class RecipeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${spring.data.web.pageable.default-page-size}")
    private Integer pageSize;

    @GetMapping("/users/{userId}/recipes")
    public @ResponseBody ResponseEntity<Page<RecipeEntity>> getAllRecipes(
            @RequestParam(defaultValue = "0", value = "page") Integer page,
            @RequestParam(defaultValue = "DESC", value = "sortOrder") String sortOrder,
            @RequestParam(defaultValue = "creationDateTime", value = "sortField") String sortField,
            @PathVariable("userId") Long userId){

        LOGGER.debug("Entering RecipeController.getAllRecipes");

        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortField);
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        Page<RecipeEntity> recipes = recipeRepository.findAllByUserEntityId(userId, pageable);
        if (recipes.isEmpty()){
            throw  new ResourceNotFoundException("No recipe found for user Id " + userId);
        }
        LOGGER.debug("Exiting RecipeController.getAllRecipes");
        return new ResponseEntity<Page<RecipeEntity>>(recipes, HttpStatus.OK);

    }

    @PostMapping("/users/{userId}/recipes")
    public @ResponseBody ResponseEntity<RecipeEntity> createRecipe(@RequestBody RecipeEntity recipe, @PathVariable("userId") Long userId){

        LOGGER.debug("Entering RecipeController.createRecipe");

        Optional<UserEntity> userEntityOpt = userRepository.findById(userId);

        RecipeEntity recipeEntity = userEntityOpt.map((user) -> {
            recipe.setUserEntity(user);
            return recipeRepository.save(recipe);
        }).orElseThrow(()-> new ResourceNotFoundException("No user found with user Id " + userId));

        LOGGER.debug("Exiting RecipeController.createRecipe");
        return new ResponseEntity<RecipeEntity>(recipeEntity, HttpStatus.CREATED);
    }

    @PutMapping("/users/{userId}/recipes")
    public @ResponseBody ResponseEntity<RecipeEntity> updateRecipe(@RequestBody RecipeEntity recipeEntity, @PathVariable("userId") Long userId){

        LOGGER.debug("Entering RecipeController.updateRecipe");

        Optional<RecipeEntity> recipeEntityOpt = recipeRepository.findByIdAndUserEntityId(recipeEntity.getId(), userId);

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
            LOGGER.debug("Exiting RecipeController.updateRecipe");
            return new ResponseEntity<>(recipeRepository.save(recipeEntityOpt.get()), HttpStatus.OK);
        }else{
            LOGGER.debug("Exiting RecipeController.updateRecipe");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{userId}/recipes/{recipeId}")
    public @ResponseBody ResponseEntity<Long> deleteRecipe(@PathVariable("recipeId") Long recipeId, @PathVariable("userId") Long userId){

        LOGGER.debug("Entering RecipeController.deleteRecipe");

        Optional<RecipeEntity> recipeEntityOpt = recipeRepository.findByIdAndUserEntityId(recipeId, userId);

        recipeEntityOpt.map((recipeEntity) -> {
            recipeRepository.delete(recipeEntity);
            return true;
        }).orElseThrow(() -> new ResourceNotFoundException("No recipe found for recipe Id "+recipeId+ " user Id "+userId));

        LOGGER.debug("Exiting RecipeController.deleteRecipe");
        return new ResponseEntity<Long>(recipeId, HttpStatus.NO_CONTENT);
    }
}
