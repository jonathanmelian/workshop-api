package com.workshop.rest;


import com.workshop.constants.HelloWorldConstants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    private final String hello = "Hello";

    @RequestMapping("/hello")
    public String hello() {
        return HelloWorldConstants.OPEN_HELLO;
    }


}