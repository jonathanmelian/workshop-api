package com.workshop.rest;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import java.io.IOException;

@RestController
public class HelloWorldController {

    private final String hello = "Hello";

    @RequestMapping("/hello")
    public String hello() {
        return hello;
    }


    @ExceptionHandler(ValidationException.class)
    protected void handleHelloWorldValidationException(ValidationException ex,
                                                         HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

}
