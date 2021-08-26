package com.example.springsecurity.Security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScheduleController {
    @GetMapping("/main")
    public String mainPage(){
        return "main";
    }
}
