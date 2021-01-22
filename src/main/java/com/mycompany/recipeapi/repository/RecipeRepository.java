package com.mycompany.recipeapi.repository;

import com.mycompany.recipeapi.entity.RecipeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends PagingAndSortingRepository<RecipeEntity, Long> {

}
