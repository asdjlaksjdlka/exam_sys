package com.qf.examsys.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;

@ControllerAdvice
public class GobalException {

    @ExceptionHandler(AuthenticationException.class)
    public String notPermsException(AuthenticationException ae){
        //跳转资源
        return "notPerms";
    }
}
