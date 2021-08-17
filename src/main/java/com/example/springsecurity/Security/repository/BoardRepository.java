package com.example.springsecurity.Security.repository;

import com.example.springsecurity.Security.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
