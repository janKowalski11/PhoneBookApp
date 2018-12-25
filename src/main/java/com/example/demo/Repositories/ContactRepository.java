package com.example.demo.Repositories;
/*
Author: BeGieU
Date: 25.12.2018
*/

import com.example.demo.Model.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact,Long>
{
}
