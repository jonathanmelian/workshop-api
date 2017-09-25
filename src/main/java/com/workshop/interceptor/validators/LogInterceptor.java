package com.workshop.interceptor.validators;

import com.nimbusds.jwt.SignedJWT;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LogInterceptor extends HandlerInterceptorAdapter {

    static Logger logger = Logger.getLogger(LogInterceptor.class);

    static{
        BasicConfigurator.configure();
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if(request.getHeader("Authorization") != null){
            String accessToken = request.getHeader("Authorization").replace("Bearer", "");
            String username = SignedJWT.parse(accessToken).getJWTClaimsSet().getCustomClaim("username").toString();
            logger.info("User " + username + " is requesting " + request.getRequestURI());
        }else{
            logger.info("Unknown user is requesting " + request.getRequestURI());
        }

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        logger.info("After handling the request");
        super.postHandle(request, response, handler, modelAndView);
    }
}