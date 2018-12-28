package com.example.demo.services;
/*
Author: BeGieU
Date: 25.12.2018
*/

import com.example.demo.model.Contact;
import commands.ContactCommand;

import java.util.Set;

public interface ContactService
{
    Set<Contact> getContacts();

    Contact findById(Long id);

    ContactCommand saveCommand(ContactCommand command);

    ContactCommand findCommandById(Long id);

    void deleteById(Long id);

}
