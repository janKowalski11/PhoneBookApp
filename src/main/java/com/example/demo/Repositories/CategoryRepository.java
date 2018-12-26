package com.example.demo.Repositories;
/*
Author: BeGieU
Date: 26.12.2018
*/

import com.example.demo.Model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long>
{
    Optional<Category> findByDescription(String description);
}
