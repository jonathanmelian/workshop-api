package com.workshop.interceptor.validators;

import com.nimbusds.jwt.SignedJWT;
import com.workshop.constants.HelloWorldConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class LogInterceptor extends HandlerInterceptorAdapter {


    static {
        BasicConfigurator.configure();
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (request.getHeader("Authorization") != null) {
            String accessToken = request.getHeader(HelloWorldConstants.AUTHORIZATION).replace(HelloWorldConstants.BEARER, HelloWorldConstants.EMPTY_STRING);
            String username = SignedJWT.parse(accessToken).getJWTClaimsSet().getCustomClaim(HelloWorldConstants.USERNAME).toString();
            log.debug("User " + username + " is requesting " + request.getRequestURI());
        } else {
            log.debug("Unknown user is requesting " + request.getRequestURI());
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.debug("After handling the request");
        super.postHandle(request, response, handler, modelAndView);
    }
}