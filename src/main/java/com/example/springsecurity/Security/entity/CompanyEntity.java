package com.example.springsecurity.Security.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Table;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String companyName;

    @NonNull
    @Column(length = 10, unique = true)
    private String companyNum; // 사업자 번호
//
//    @NonNull
//    private String address;
//
//    @NonNull
//    private String phone;
//
//    @NonNull
//    private LocalDateTime permitTimeBeg;
//
//    @NonNull
//    private LocalDateTime permitTimeEnd;

}
