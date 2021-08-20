package com.example.springsecurity.Security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    private String id;

    @NotBlank(message = "반드시 입력해야합니다.")
    @Size(min=2, message = "두 글자 이상 입력해야합니다.")
    private String companyName;

    @NotBlank(message = "반드시 입력해야합니다.")
    @Size(min=10, max=10, message = "사업자번호는 10자리입니다.")
    private String companyNum;

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
