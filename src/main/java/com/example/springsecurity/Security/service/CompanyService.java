package com.example.springsecurity.Security.service;

import com.example.springsecurity.Security.dto.CompanyDto;
import com.example.springsecurity.Security.entity.CompanyEntity;
import com.example.springsecurity.Security.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    public void enrollCompany(CompanyDto companyDto){
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setCompanyNum(companyDto.getCompanyNum());
        companyEntity.setCompanyName(companyDto.getCompanyName());
        companyRepository.save(companyEntity);
    }

}
