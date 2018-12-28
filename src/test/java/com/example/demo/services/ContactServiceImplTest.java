package com.example.demo.services;

import com.example.demo.converters.ContactCommandToContact;
import com.example.demo.converters.ContactToContactCommand;
import com.example.demo.model.Contact;
import com.example.demo.repositories.ContactRepository;
import commands.ContactCommand;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ContactServiceImplTest
{

    ContactServiceImpl contactService;

    @Mock
    ContactRepository contactRepository;

    @Mock
    ContactCommandToContact contactCommandToContact;

    @Mock
    ContactToContactCommand contactToContactCommand;


    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        contactService = new ContactServiceImpl(contactRepository,contactCommandToContact,contactToContactCommand);
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

    @Test
    public void testFindById()
    {
        Contact contact=new Contact();
        contact.setId(1L);

        when(contactRepository.findById(any())).thenReturn(Optional.of(contact));

        Contact returnedContact=contactService.findById(1L);

        assertNotNull(returnedContact);
        assertEquals(contact.getId(), returnedContact.getId());
        verify(contactRepository,times(1)).findById(any());
    }

    @Test
    public void testSaveCommand()
    {
        //given
//        ContactCommand contactCommand=new ContactCommand();
//        contactCommand.setId(1L);
//
//        when(contactService.saveCommand(any())).thenReturn(contactCommand);



    }
}