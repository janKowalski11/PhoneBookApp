package com.example.demo.services;
/*
Author: BeGieU
Date: 25.12.2018
*/

import com.example.demo.converters.ContactCommandToContact;
import com.example.demo.converters.ContactToContactCommand;
import com.example.demo.model.Contact;
import com.example.demo.repositories.ContactRepository;
import commands.ContactCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ContactServiceImpl implements ContactService
{

    private final ContactRepository contactRepository;
    private final ContactCommandToContact toContact;
    private final ContactToContactCommand toContactCommand;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository, ContactCommandToContact toContact, ContactToContactCommand toContactCommand)
    {
        this.contactRepository = contactRepository;
        this.toContact = toContact;
        this.toContactCommand = toContactCommand;
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

    @Override
    public Contact findById(Long id)
    {
        Optional<Contact> contactOptional = contactRepository.findById(id);
        if (!contactOptional.isPresent())
        {
            throw new RuntimeException("Contact not found, it's id " + id);
        }

        return contactOptional.get();
    }

    @Override
    @Transactional
    public ContactCommand saveCommand(ContactCommand command)
    {
        System.out.println("\n\nsaveCommand COMMAND :  " + command);

        Contact detachedContact = toContact.convert(command);

        Contact savedContact = contactRepository.save(detachedContact);

        return toContactCommand.convert(savedContact);
    }

    @Override
    public ContactCommand findCommandById(Long id)
    {
        return toContactCommand.convert(this.findById(id));
    }

    @Override
    public void deleteById(Long id)
    {
        contactRepository.deleteById(id);
    }
}
