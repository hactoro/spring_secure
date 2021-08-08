package com.example.springsecurity.Security.controller;

import com.example.springsecurity.Security.dto.MemberDto;
import com.example.springsecurity.Security.entity.MemberEntity;
import com.example.springsecurity.Security.repository.MemberRepository;
import com.example.springsecurity.Security.service.MemberService;
import lombok.AllArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

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
        System.out.println("------ sign up -------");
        memberService.joinUser(memberDto);
        return "redirect:/user/login";
//        return "login";
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

    @GetMapping("/user-list")
    public ModelAndView listAll(Model model){
//        model.addAttribute("name", "haseongdae");
//        return "testmodel";

        ModelAndView mv = new ModelAndView();
        mv.setViewName("testmodel");
//        memberRepository.findAll().forEach(
//                u -> mv.addObject(u.getEmail(), u.getPassword())
//        );
        List<MemberEntity> userList = memberRepository.findAll();
        mv.addObject("userList",userList);

//        mv.
        return mv;
    }

}
