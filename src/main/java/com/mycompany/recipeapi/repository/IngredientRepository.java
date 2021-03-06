package com.mycompany.recipeapi.repository;

import com.mycompany.recipeapi.entity.IngredientEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends PagingAndSortingRepository<IngredientEntity, Long> {
}
