package com.example.springsecurity.Security.exception;

import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView notFindPage(ModelAndView model, Exception e){
        model.addObject("exception", e);
        model.setViewName("/error");
        return model;
    }
}
