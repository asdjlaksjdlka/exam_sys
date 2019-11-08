package com.qf.examsys.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GobalException {

    @ExceptionHandler(AuthorizationException.class)
    public String notPermsException(AuthorizationException ae){
        //跳转资源
        return "redirect:/noPerm.html";
    }
}
