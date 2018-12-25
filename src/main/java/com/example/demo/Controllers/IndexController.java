package com.example.demo.Controllers;
/*
Author: BeGieU
Date: 25.12.2018
*/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController
{
    @RequestMapping({"","/index","/"})
    public String getIndexPage()
    {
        return "index";
    }
}
