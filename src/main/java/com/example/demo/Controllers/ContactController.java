package com.example.demo.Controllers;
/*
Author: BeGieU
Date: 26.12.2018
*/

import com.example.demo.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ContactController
{
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService)
    {
        this.contactService = contactService;
    }

    @GetMapping("/contact/{id}/show")
    public String showById(@PathVariable String id, Model model)
    {
        model.addAttribute("contact",contactService.findById(Long.valueOf(id)));

        return "contact/show";
    }
}
