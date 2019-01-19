package com.example.demo.converters;
/*
Author: BeGieU
Date: 26.12.2018
*/

import com.example.demo.model.Contact;
import commands.CategoryCommand;
import commands.ContactCommand;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ContactCommandToContact implements Converter<ContactCommand, Contact>
{

    private final CategoryCommandToCategory toCategory;

    @Autowired
    public ContactCommandToContact(CategoryCommandToCategory toCategory)
    {
        this.toCategory = toCategory;
    }

    @Synchronized
    @Nullable
    @Override
    public Contact convert(ContactCommand source)
    {
        if(source == null)
        {
            return null;
        }

        final Contact contact = new Contact();
        contact.setId(source.getId());
        contact.setFirstName(source.getFirstName());
        contact.setLastName(source.getLastName());
        contact.setNumber(source.getNumber());
        contact.setImage(source.getImage());

        Set<CategoryCommand> categoriesCommand = source.getCategories();
        if (categoriesCommand != null && categoriesCommand.size() > 0)
        {
            categoriesCommand.forEach(category ->
            {
                contact.getCategories().add(toCategory.convert(category));
            });
        }

        return contact;

    }
}
