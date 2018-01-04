package com.workshop.interceptor.validators;


public interface Validator {
    String getHeaderKey();

    String getHeaderValue();

    String getErrorMessage();

}