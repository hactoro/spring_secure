package com.example.springsecurity.Security.repository;

import com.example.springsecurity.Security.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
}
