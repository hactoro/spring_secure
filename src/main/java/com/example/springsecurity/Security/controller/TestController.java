package com.example.springsecurity.Security.controller;

import com.example.springsecurity.Security.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "teset";
    }

//    @PostMapping("/user/signup")
//    public String enrollUser(){
//        System.out.println("------ sign up -------");
////        memberService.joinUser(memberDto);
//        return "redirect:/user/login";
//    }
}
