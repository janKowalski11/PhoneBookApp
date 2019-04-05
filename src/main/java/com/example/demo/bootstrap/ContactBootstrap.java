package com.example.demo.bootstrap;
/*
Author: BeGieU
Date: 26.12.2018
*/

import com.example.demo.model.Category;
import com.example.demo.model.Contact;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ContactBootstrap implements ApplicationListener<ContextRefreshedEvent>
{
    private final CategoryRepository categoryRepository;
    private final ContactRepository contactRepository;

    @Autowired
    public ContactBootstrap(CategoryRepository categoryRepository, ContactRepository contactRepository)
    {
        this.categoryRepository = categoryRepository;
        this.contactRepository = contactRepository;
    }

    private List<Contact> getContacts()
    {
        List<Contact> contactList = new ArrayList<>(2);


        //downloading categories from DB, initialized ind data.sql
        Optional<Category> workCategoryOpt = categoryRepository.findByDescription("Work");
        if (!workCategoryOpt.isPresent())
        {
            throw new RuntimeException("Work Category is not found");
        }

        Optional<Category> schoolCategoryOpt = categoryRepository.findByDescription("School");
        if (!schoolCategoryOpt.isPresent())
        {
            throw new RuntimeException("school Category is not found");
        }

        Optional<Category> friendCategoryOpt = categoryRepository.findByDescription("Friend");
        if (!friendCategoryOpt.isPresent())
        {
            throw new RuntimeException("friend Category is not found");
        }

        Optional<Category> importantCategoryOpt = categoryRepository.findByDescription("Important");
        if (!importantCategoryOpt.isPresent())
        {
            throw new RuntimeException("Important Category is not found");
        }

        Category workCategory = workCategoryOpt.get();
        Category schoolCategory = schoolCategoryOpt.get();
        Category friendCategory = friendCategoryOpt.get();
        Category importantCategory = importantCategoryOpt.get();

        Contact jonContact = new Contact();
        jonContact.setFirstName("Jon");
        jonContact.setLastName("Bones");
        jonContact.setNumber(997997997L);
        jonContact.getCategories().add(workCategory);
        jonContact.getCategories().add(friendCategory);
        jonContact.getCategories().add(importantCategory);

        contactList.add(jonContact);

        Contact gussContact = new Contact();
        gussContact.setFirstName("Alexander");
        gussContact.setLastName("Mauler");
        gussContact.setNumber(123123123L);
        gussContact.getCategories().add(schoolCategory);
        gussContact.getCategories().add(importantCategory);

        contactList.add(gussContact);


        System.out.println();
        return contactList;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        contactRepository.saveAll(this.getContacts());
    }
}
