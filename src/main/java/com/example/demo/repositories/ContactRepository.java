package com.example.demo.repositories;
/*
Author: BeGieU
Date: 25.12.2018
*/

import com.example.demo.model.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact,Long>
{
}
