package com.example.demo.Controllers;

import com.example.demo.Model.Contact;
import com.example.demo.services.ContactService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class IndexControllerTest
{

    @Mock
    ContactService contactService;

    @Mock
    Model model;

    IndexController indexController;


    @Before
    public void setUp() throws Exception
    {
        initMocks(this);
        indexController = new IndexController(contactService);
    }

    @Test
    public void getIndexPage()
    {
        Set<Contact> contactSet = new HashSet<>();
        Contact contact = new Contact();
        contact.setId(1L);
        contactSet.add(contact);

        when(contactService.getContacts()).thenReturn(contactSet);

        //init arg captor
        ArgumentCaptor<Set<Contact>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        String viewName = indexController.getIndexPage(model);

        assertEquals("index", viewName);
        verify(contactService, times(1)).getContacts();
        /*arg captor captures the arg  that was put on the contactService.getContacts() place in line
        model.addAttribute("contacts",contactService.getContacts());*/
        verify(model,times(1)).addAttribute(eq("contacts"),argumentCaptor.capture());

        Set<Contact> setInController=argumentCaptor.getValue();

        assertEquals(1, setInController.size());


    }
}