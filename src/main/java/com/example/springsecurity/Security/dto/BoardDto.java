package com.example.springsecurity.Security.dto;

import lombok.Data;

@Data
public class BoardDto {

    private Long id;
    private String title;
    private String writer;
    private String content;
    private int likeCount;
}
