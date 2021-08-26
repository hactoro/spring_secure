package com.example.springsecurity.Security.exception;

import com.example.springsecurity.Security.prop.ErrorEnum;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private ErrorEnum errorEnum;

    public CustomException(ErrorEnum e){
        super(e.getMessage());
        this.errorEnum = e;
    }
}
