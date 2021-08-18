package com.example.springsecurity.Security.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String writer;
    private String content;
    private String loginUser; //로그인 user

    @OneToOne
    private LikeEntity likeEntity;
}
