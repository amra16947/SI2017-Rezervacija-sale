package com.ws1001.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/")
public class TestController {


    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String testMe() {
        String tst = "Test test test achtung achtung";
        return tst;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "protected", method = RequestMethod.GET)
    public String giveItToAdmin(){
        String tst = "Hi Admin";
        return tst;
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @RequestMapping(value = "/zbornica")
    public String giveItToTeacher() {
        String tst = "Zbornica";
        return tst;
    }
}

  