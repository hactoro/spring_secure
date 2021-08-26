package com.example.springsecurity.Security.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
public class ErrorDto {
    private String errorCode;
    private String errorMessage;

    @Override
    public String toString(){
        return "hello java world";
    }

}
