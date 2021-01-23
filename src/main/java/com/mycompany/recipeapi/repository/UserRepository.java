package com.mycompany.recipeapi.repository;

import com.mycompany.recipeapi.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
}
