package com.mycompany.recipeapi.repository;

import com.mycompany.recipeapi.entity.RecipeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepository extends PagingAndSortingRepository<RecipeEntity, Long> {

    Page<RecipeEntity> findAllByUserEntityId(Long userId, Pageable pageable);

    Optional<RecipeEntity> findByIdAndUserEntityId(Long id, Long userId);
}
