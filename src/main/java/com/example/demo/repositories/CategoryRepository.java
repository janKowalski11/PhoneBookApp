package com.example.demo.repositories;
/*
Author: BeGieU
Date: 26.12.2018
*/

import com.example.demo.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long>
{
    Optional<Category> findByDescription(String description);
}
