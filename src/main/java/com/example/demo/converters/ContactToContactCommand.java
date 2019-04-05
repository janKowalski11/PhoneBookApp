package com.example.demo.converters;
/*
Author: BeGieU
Date: 27.12.2018
*/

import com.example.demo.model.Category;
import com.example.demo.model.Contact;
import commands.ContactCommand;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ContactToContactCommand implements Converter<Contact, ContactCommand>
{
    private CategoryToCategoryCommand toCategoryCommand;

    @Autowired
    public ContactToContactCommand(CategoryToCategoryCommand toCategoryCommand)
    {
        this.toCategoryCommand = toCategoryCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public ContactCommand convert(Contact source)
    {
        if (source == null)
        {
            return null;
        }

        final ContactCommand contactCommand = new ContactCommand();
        contactCommand.setId(source.getId());
        contactCommand.setFirstName(source.getFirstName());
        contactCommand.setLastName(source.getLastName());
        contactCommand.setNumber(source.getNumber());
        contactCommand.setImage(source.getImage());

        Set<Category> categories = source.getCategories();
        if (categories != null && categories.size() > 0)
        {
            categories.forEach(category ->
            {
                contactCommand.getCategories().add(toCategoryCommand.convert(category));
            });
        }

        return contactCommand;
    }
}
