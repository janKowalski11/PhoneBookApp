package com.example.demo.controllers;
/*
Author: BeGieU
Date: 26.12.2018
*/

import com.example.demo.services.ContactService;
import commands.ContactCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("contact", contactService.findById(Long.valueOf(id)));

        return "contact/show";
    }

    @GetMapping("/contact/new")
    public String newRecipe(Model model)
    {
        model.addAttribute("contact", new ContactCommand());

        return "contact/contact_form";
    }


    @PostMapping("contact")
    public String addOrUpdate(@ModelAttribute("contact") ContactCommand contact)
    {
        ContactCommand savedCommand = contactService.saveCommand(contact);

        return "redirect:/contact/" + savedCommand.getId() + "/show";
    }

    @GetMapping("/contact/{id}/update")
    public String update(@PathVariable String id, Model model)
    {
        model.addAttribute("contact", contactService.findCommandById(Long.valueOf(id)));
        return "contact/contact_form";
    }

    @GetMapping("contact/{id}/delete")
    public String delete(@PathVariable String id)
    {
        contactService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }

}
