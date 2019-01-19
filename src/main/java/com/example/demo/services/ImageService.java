package com.example.demo.services;
/*
Author: BeGieU
Date: 18.01.2019
*/

import org.springframework.web.multipart.MultipartFile;

public interface ImageService
{
    void saveImage(Long contactId, MultipartFile image);
}
