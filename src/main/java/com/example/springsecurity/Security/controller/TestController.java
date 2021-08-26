package com.example.springsecurity.Security.controller;

import com.example.springsecurity.Security.dto.BoardDto;
import com.example.springsecurity.Security.dto.MemberDto;
import com.example.springsecurity.Security.dto.ValidDto;
import com.example.springsecurity.Security.exception.CustomException;
import com.example.springsecurity.Security.prop.ErrorEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
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
    @GetMapping("/valid")
    public String validTest(@ModelAttribute ValidDto validDto, Model model){
        model.addAttribute("validDto", validDto);
        return "validator/form";
    }
    @PostMapping("/valid/input")
    public String validTestInput(@Valid ValidDto validDto, BindingResult bindingResult, Model model){
        model.addAttribute("validDto", validDto);


        if (bindingResult.hasErrors()){
            for (ObjectError allError : bindingResult.getAllErrors()) {
                System.out.println(allError);
            }
            System.out.println("-------------valid error--------");

            return "/validator/form";
        }
        System.out.println("-------------no valid error---------");
        return "/login";
    }

    @GetMapping("/custom_error")
    public void customError(){
        throw new CustomException(ErrorEnum.RUNTIME_EXCEPTION);
    }


}


