package com.example.demo.Model;
/*
Author: BeGieU
Date: 25.12.2018
*/


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PhoneBook extends BaseEntity
{
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phoneBook")
    private Set<Contact> contactSet = new HashSet<>();

    public Set<Contact> getContactSet()
    {
        return contactSet;
    }

    public void setContactSet(Set<Contact> contactSet)
    {
        this.contactSet = contactSet;
    }
}
