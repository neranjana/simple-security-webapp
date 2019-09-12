package com.neranjana.springsecurity.tryout.simplesecuritywebapp;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/greetings")
public class SimpleRestController {


    @RequestMapping(value="/morning", method= RequestMethod.GET)
    @ResponseBody
    public String getMorningGreeting() {

        return "Good Morning";
    }

    @RequestMapping(value="/evening", method= RequestMethod.GET)
    public String getEveningGreeting() {

        return "Good Evening";
    }
}
