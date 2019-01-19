package com.example.demo.model;
/*
Author: BeGieU
Date: 25.12.2018
*/


import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Contact extends BaseEntity
{
    private String firstName;
    private String lastName;
    private Long number;
    @Lob
    private Byte[] image;


    @ManyToMany
    private Set<Category> categories = new HashSet<>();

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public Long getNumber()
    {
        return number;
    }

    public void setNumber(Long number)
    {
        this.number = number;
    }

    public Set<Category> getCategories()
    {
        return categories;
    }

    public void setCategories(Set<Category> categories)
    {
        this.categories = categories;
    }

    public Byte[] getImage()
    {
        return image;
    }

    public void setImage(Byte[] image)
    {
        this.image = image;
    }
}


