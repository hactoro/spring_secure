package com.example.springsecurity.Security.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class LikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private BoardEntity boardEntity;

    private int count;
}
