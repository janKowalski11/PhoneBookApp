package com.example.demo.services;
/*
Author: BeGieU
Date: 25.12.2018
*/

import com.example.demo.Model.Contact;

import java.util.Set;

public interface ContactService
{
    Set<Contact> getContacts();

    Contact findById(Long id);

}
