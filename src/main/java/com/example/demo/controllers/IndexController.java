package com.example.demo.controllers;
/*
Author: BeGieU
Date: 25.12.2018
*/

import com.example.demo.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController
{

    private final ContactService contactService;

    @Autowired
    public IndexController(ContactService contactService)
    {
        this.contactService = contactService;
    }

    @RequestMapping({"","/index","/"})
    public String getIndexPage(Model model)
    {
        model.addAttribute("contacts",contactService.getContacts());
        return "index";
    }
}
