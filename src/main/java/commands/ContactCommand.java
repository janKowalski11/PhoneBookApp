package commands;
/*
Author: BeGieU
Date: 26.12.2018
*/

import com.example.demo.model.Category;

import java.util.HashSet;
import java.util.Set;

public class ContactCommand extends BasicCommand
{

    private String firstName;
    private String lastName;
    private Long number;

    private Set<CategoryCommand> categories = new HashSet<>();


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

    public Set<CategoryCommand> getCategories()
    {
        return categories;
    }

    public void setCategories(Set<CategoryCommand> categories)
    {
        this.categories = categories;
    }
}
