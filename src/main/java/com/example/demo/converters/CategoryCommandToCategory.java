package com.example.demo.converters;
/*
Author: BeGieU
Date: 26.12.2018
*/

import com.example.demo.model.Category;
import commands.CategoryCommand;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category>
{
    @Override
    public Category convert(CategoryCommand source)
    {
        final Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());

        return category;
    }
}
