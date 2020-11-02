package com.upgrad.mba.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @GetMapping(value= {"/sayHelloMovie"})
    public String sayHello(){
        return "Hello World To All From MovieController";
    }
}
