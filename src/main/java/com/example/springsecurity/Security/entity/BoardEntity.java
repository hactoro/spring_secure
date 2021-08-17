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

    @OneToOne
    private LikeEntity likeEntity;
}
