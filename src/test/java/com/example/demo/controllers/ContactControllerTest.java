package com.example.demo.controllers;

import com.example.demo.model.Contact;
import com.example.demo.services.ContactService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class ContactControllerTest
{

    @Mock
    ContactService contactService;

    ContactController contactController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        contactController = new ContactController(contactService);
        mockMvc = MockMvcBuilders.standaloneSetup(contactController).build();
    }

    @Test
    public void showById() throws Exception
    {
        //given
        Contact contact = new Contact();
        contact.setId(1L);

        when(contactService.findById(any())).thenReturn(contact);

        mockMvc.perform(get("/contact/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("contact/show"))
                .andExpect(model().attributeExists("contact"));

    }
}