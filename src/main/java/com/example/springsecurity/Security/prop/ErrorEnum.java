package com.example.springsecurity.Security.prop;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ErrorEnum {
    RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "E0001", "잘못된 요청입니다.");

    private final HttpStatus status;
    private final String code;
    private String message;

    ErrorEnum(HttpStatus status, String code){
        this.status = status;
        this.code = code;
    }

    ErrorEnum(HttpStatus status, String code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
