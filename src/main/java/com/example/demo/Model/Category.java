package com.example.demo.Model;
/*
Author: BeGieU
Date: 25.12.2018
*/

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category extends BaseEntity
{
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Contact> contacts=new HashSet<>();

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Set<Contact> getContacts()
    {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts)
    {
        this.contacts = contacts;
    }
}
