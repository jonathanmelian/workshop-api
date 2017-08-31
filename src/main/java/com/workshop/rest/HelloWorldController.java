package com.workshop.rest;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    private final String hello = "Hello";

    @RequestMapping("/hello")
    public String hello() {
        return hello;
    }


//    @ExceptionHandler(ValidationException.class)
//    protected void handleHelloWorldValidationException(ValidationException ex,
//                                                         HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
//    }

}
