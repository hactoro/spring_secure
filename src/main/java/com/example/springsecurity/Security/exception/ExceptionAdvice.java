package com.example.springsecurity.Security.exception;

import com.example.springsecurity.Security.dto.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @ExceptionHandler(UsernameNotFoundException.class)
    public String notFindUser( Exception e){
        System.out.println("----------exception handler --UsernameNotFountException --------");
        System.out.println(e.getMessage());
        return e.getMessage();
    }
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public String failAuthentication(Exception e){
        System.out.println("------------exception handler fail auth");
        System.out.println(e.getMessage());
        return e.getMessage();
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorDto> exceptionHandler(CustomException e){
        System.out.println("custom exception to-string : ");
        System.out.println(e.toString());
        return ResponseEntity
                .status(e.getErrorEnum().getStatus())
                .body(ErrorDto.builder()
                        .errorCode(e.getErrorEnum().getCode())
                        .errorMessage(e.getErrorEnum().getMessage())
                        .build());
    }
}
