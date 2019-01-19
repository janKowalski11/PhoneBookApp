package com.example.demo.services;

import com.example.demo.model.Contact;
import com.example.demo.repositories.ContactRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ImageServiceImplTest
{
    @Mock
    ContactRepository contactRepository;

    ImageService imageService;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        imageService = new ImageServiceImpl(contactRepository);
    }


    @Test
    public void testSaveImage() throws Exception
    {
        //given
        Contact contact = new Contact();
        contact.setId(1L);
        Optional<Contact> contactOptional = Optional.of(contact);
        when(contactRepository.findById(anyLong())).thenReturn(contactOptional);

        ArgumentCaptor<Contact> argumentCaptor = ArgumentCaptor.forClass(Contact.class);


        MultipartFile multipartFile = new MockMultipartFile("imageFile", "test.txt",
                "text/plain", "TESTTESTTEST".getBytes());

        //when
        imageService.saveImage(1L, multipartFile);

        //then
        verify(contactRepository, times(1)).save(argumentCaptor.capture());
        Contact savedContact = argumentCaptor.getValue();

        assertEquals(multipartFile.getBytes().length, savedContact.getImage().length);


    }
}