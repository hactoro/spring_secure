package com.example.springsecurity.Security.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ValidDto {

    @NotBlank(message = "내용을 채워주세요.")
    private String content;

    @NotBlank(message = "내용을 채워주세요.")
    private String title;

}
