package com.example.demo.converters;

import com.example.demo.model.Category;
import com.example.demo.model.Contact;
import commands.ContactCommand;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactToContactCommandTest
{

    ContactToContactCommand toContactCommand;

    final Long ID=1L;
    final String FIRST_NAME="fname";
    final String LAST_NAME="lname";
    final Long NUMBER=997L;
    final Long CATEGORY_ID_1=1L;
    final Long CATEGORY_ID_2=2L;

    @Before
    public void setUp() throws Exception
    {
        toContactCommand = new ContactToContactCommand(new CategoryToCategoryCommand());
    }

    @Test
    public void testNullObject()
    {
        assertNull(toContactCommand.convert(null));
    }

    @Test
    public void testEmptyObject()
    {
        assertNotNull(toContactCommand.convert(new Contact()));
    }


    @Test
    public void convert()
    {
        //given
        Contact contact=new Contact();
        contact.setId(ID);
        contact.setFirstName(FIRST_NAME);
        contact.setLastName(LAST_NAME);
        contact.setNumber(NUMBER);

        Category category1=new Category();
        category1.setId(1L);

        Category category2=new Category();
        category2.setId(2L);

        contact.getCategories().add(category1);
        contact.getCategories().add(category2);

        //when
        ContactCommand contactCommand=toContactCommand.convert(contact);

        //then
        assertNotNull(contactCommand);
        assertEquals(ID, contactCommand.getId());
        assertEquals(FIRST_NAME, contactCommand.getFirstName());
        assertEquals(LAST_NAME, contactCommand.getLastName());
        assertEquals(NUMBER, contactCommand.getNumber());

        assertEquals(2,contactCommand.getCategories().size());




    }
}