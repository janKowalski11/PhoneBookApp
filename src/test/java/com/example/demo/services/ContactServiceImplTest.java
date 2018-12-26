package com.example.demo.services;

import com.example.demo.Model.Contact;
import com.example.demo.Repositories.ContactRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ContactServiceImplTest
{

    ContactServiceImpl contactService;

    @Mock
    ContactRepository contactRepository;


    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        contactService = new ContactServiceImpl(contactRepository);
    }

    @Test
    public void getContacts()
    {
        Contact contact=new Contact();
        contact.setId(1L);

        Set<Contact> contactSet=new HashSet<>();
        contactSet.add(contact);

        when(contactRepository.findAll()).thenReturn(contactSet);

        assertEquals(1, contactService.getContacts().size());
        verify(contactRepository,times(1)).findAll();

    }
}