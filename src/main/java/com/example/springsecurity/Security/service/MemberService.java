package com.example.springsecurity.Security.service;

import com.example.springsecurity.Security.dto.MemberDto;
import com.example.springsecurity.Security.entity.MemberEntity;
import com.example.springsecurity.Security.prop.RoleEnum;
import com.example.springsecurity.Security.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {
    private MemberRepository memberRepository;

    @Transactional
    public Long joinUser(MemberDto memberDto){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        return memberRepository.save(memberDto.toEntity()).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException, InternalAuthenticationServiceException {

        System.out.println("-----MemberService------loadUserByUsername------");
        System.out.println("userEmail : " + userEmail);

        Optional<MemberEntity> userEntityWrapper = memberRepository.findByEmail(userEmail);
        MemberEntity userEntity;
        userEntity = userEntityWrapper.get();
//        try {
//             userEntity = userEntityWrapper.get();
//        }catch(Exception e){
//            System.out.println("가입된 회원이 아닙니다!!!");
//            System.out.println(e.getMessage());
//            throw new UsernameNotFoundException("가입된 회원이 아닙니다.");
//        }
        System.out.println("여기는 실행 안되겠지?");
        List<GrantedAuthority> authorities = new ArrayList<>();

        if(("admin@example.com").equals(userEmail)){
            authorities.add(new SimpleGrantedAuthority(RoleEnum.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(RoleEnum.MEMBER.getValue()));
        }
        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }
}
