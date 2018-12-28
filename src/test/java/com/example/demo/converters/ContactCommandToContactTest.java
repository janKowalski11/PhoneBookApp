package com.example.demo.converters;

import com.example.demo.model.Contact;
import commands.CategoryCommand;
import commands.ContactCommand;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactCommandToContactTest
{

    ContactCommandToContact toContact;
    
    final Long ID=1L;
    final String FIRST_NAME="fname";
    final String LAST_NAME="lname";
    final Long NUMBER=997L;
    final Long CATEGORY_ID_1=1L;
    final Long CATEGORY_ID_2=2L;
    

    @Before
    public void setUp() throws Exception
    {
        toContact = new ContactCommandToContact(new CategoryCommandToCategory());
    }

    @Test
    public void testNullObject()
    {
       assertNull( toContact.convert(null));
    }

    @Test
    public void testEmptyObj()
    {
        assertNotNull(toContact.convert(new ContactCommand()));
    }

    @Test
    public void testConvertWithCategories()
    {
        //given
        ContactCommand contactCommand=new ContactCommand();
        contactCommand.setId(ID);
        contactCommand.setFirstName(FIRST_NAME);
        contactCommand.setLastName(LAST_NAME);
        contactCommand.setNumber(NUMBER);

        CategoryCommand categoryCommand1=new CategoryCommand();
        categoryCommand1.setId(CATEGORY_ID_1);
        CategoryCommand categoryCommand2=new CategoryCommand();
        categoryCommand2.setId(CATEGORY_ID_2);

        contactCommand.getCategories().add(categoryCommand1);
        contactCommand.getCategories().add(categoryCommand2);

        //when
        Contact contact= toContact.convert(contactCommand);

        assertNotNull(contact);
        assertEquals(ID, contact.getId());
        assertEquals(FIRST_NAME, contact.getFirstName());
        assertEquals(LAST_NAME, contact.getLastName());
        assertEquals(NUMBER, contact.getNumber());

        assertEquals(2, contact.getCategories().size());

    }

    @Test
    public void testConvertWithNullCategories()
    {
        ContactCommand contactCommand=new ContactCommand();
        contactCommand.setId(ID);

        Contact contact= toContact.convert(contactCommand);

        assertEquals(0,contact.getCategories().size());

    }
}