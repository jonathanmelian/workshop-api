package com.workshop.interceptor.validators;

import org.springframework.stereotype.Component;

@Component
public class ContentTypeValidator implements Validator {
    private String headerKey = "Content-Type";
    private String headerValue = "application/json";
    private String errorMessage = "Content type is not valid";

    @Override
    public String getHeaderKey() {
        return headerKey;
    }

    @Override
    public String getHeaderValue() {
        return headerValue;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
