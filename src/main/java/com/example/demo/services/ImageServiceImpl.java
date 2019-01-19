package com.example.demo.services;
/*
Author: BeGieU
Date: 18.01.2019
*/

import com.example.demo.model.Contact;
import com.example.demo.repositories.ContactRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService
{
    private final ContactRepository contactRepository;

    public ImageServiceImpl(ContactRepository contactRepository)
    {
        this.contactRepository = contactRepository;
    }

    @Override
    public void saveImage(Long contactID, MultipartFile image)
    {
        try
        {
            Contact contact = contactRepository.findById(contactID).get();
            Byte[] imageBytes = new Byte[image.getBytes().length];

            //converting primitive bytes to wrapped Bytes
            int i = 0;
            for (byte primitiveByte : image.getBytes())
            {
                imageBytes[i++] = primitiveByte;
            }
            contact.setImage(imageBytes);

            contactRepository.save(contact);
        }
        catch (Exception e)
        {
            //todo implement exception handling
            System.out.println("Error: " + e);
            e.printStackTrace();
        }

    }
}
