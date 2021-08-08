package com.example.springsecurity.Security.controller;

import com.example.springsecurity.Security.dto.MemberDto;
import com.example.springsecurity.Security.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class MemberController {

    private MemberService memberService;

    @GetMapping("/")
    public String index(){
        return "/index";
    }

    @GetMapping("/user/signup")
    public String toSignup(){
        return "/signup";
    }

    @PostMapping("/user/signup")
    public String enrollUser(MemberDto memberDto){
        memberService.joinUser(memberDto);
        return "redirect:/user/login";
    }

    @GetMapping("/user/login")
    public String toLogin(){
        return "/login";
    }

    @GetMapping("/user/login/result")
    public String toLoginResult(){
        return "/loginSucess";
    }

    @GetMapping("/user/logout/result")
    public String toLogoutResult(){
        return "/logout";
    }

    @GetMapping("/user/denied")
    public String toDenied(){
        return "/denied";
    }

    @GetMapping("user/info")
    public String toMyInfo(){
        return "/myinfo";
    }

    @GetMapping("/admin")
    public String toAdmin(){
        return "/admin";
    }

}
