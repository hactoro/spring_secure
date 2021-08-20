package com.example.springsecurity.Security.dto;

import com.example.springsecurity.Security.entity.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
    private Long id;
    private String email;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .id(id)
                .email(email)
                .password(password)
                .build();
    }
}
