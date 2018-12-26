package com.example.demo.services;
/*
Author: BeGieU
Date: 25.12.2018
*/

import com.example.demo.Model.Contact;
import com.example.demo.Repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ContactServiceImpl implements ContactService
{

    private ContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository)
    {
        this.contactRepository = contactRepository;
    }

    @Override
    public Set<Contact> getContacts()
    {
        Iterable<Contact> contactsIterable = contactRepository.findAll();
        if (contactsIterable == null)
        {
            throw new RuntimeException("contacts repository is empty!!!");
        }

        Set<Contact> contactSet = new HashSet<>();
        contactsIterable.forEach(contact ->
        {
            contactSet.add(contact);
        });

        return contactSet;
    }
}
