package com.workshop.rest;


import com.workshop.constants.HelloWorldConstants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldUnlockedController {


    @RequestMapping("/closed/hello")
    public String hello() {
        return HelloWorldConstants.CLOSED_HELLO;
    }


}
