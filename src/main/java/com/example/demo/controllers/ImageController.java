package com.example.demo.controllers;
/*
Author: BeGieU
Date: 18.01.2019
*/

import com.example.demo.services.ContactService;
import com.example.demo.services.ImageService;
import commands.ContactCommand;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@Controller
public class ImageController
{
    private final ContactService contactService;
    private final ImageService imageService;

    public ImageController(ContactService contactService, ImageService imageService)
    {
        this.contactService = contactService;
        this.imageService = imageService;
    }

    @GetMapping("/contact/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model)
    {
        model.addAttribute("contact", contactService.findCommandById(Long.valueOf(id)));
        return "contact/image_upload_form";
    }

    @PostMapping("contact/{id}/image")
    public String saveImageToDb(@PathVariable String id, @RequestParam("imageFile") MultipartFile image)
    {
        imageService.saveImage(Long.valueOf(id), image);
        return "redirect:/contact/" + id + "/show";
    }

    @GetMapping("contact/{id}/contact_image")
    public void renderImageFromDb(@PathVariable String id, HttpServletResponse response) throws IOException
    {
        ContactCommand contactCommand = contactService.findCommandById(Long.valueOf(id));
        System.out.println(contactCommand.getImage());

        byte[] photoByteArr;
        if (contactCommand.getImage() != null)
        {
            photoByteArr = new byte[contactCommand.getImage().length];

            int i = 0;
            for (Byte wrappedByte : contactCommand.getImage())
            {
                photoByteArr[i++] = wrappedByte;//auto unboxing wrapped byte
            }
        }
        else
        {
            File defaultPhoto = new ClassPathResource("static/images/image_not_set.png").getFile();
            photoByteArr = Files.readAllBytes(defaultPhoto.toPath());
        }
        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(photoByteArr);
        IOUtils.copy(is, response.getOutputStream());

    }
}
