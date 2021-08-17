package com.example.springsecurity.Security.repository;

import com.example.springsecurity.Security.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
}
