package com.workshop.interceptor;

import com.workshop.interceptor.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import java.util.Optional;


@Service
public class HelloWorldInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private Validator[] validators;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        for (Validator validator : validators) {
            Optional<String> header = Optional.ofNullable(request.getHeader(validator.getHeaderKey()));
            if (header.isPresent()) {
                if (!header.get().equals(validator.getHeaderValue())) {
                    throw new ValidationException(validator.getErrorMessage());
                }
            } else {
                throw new ValidationException(validator.getHeaderKey() + " is missing or empty");
            }
        }
        return true;
    }

}