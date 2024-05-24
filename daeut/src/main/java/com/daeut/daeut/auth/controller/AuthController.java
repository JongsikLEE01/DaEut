package com.daeut.daeut.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/auth")
public class AuthController {
    
    @GetMapping("/member")
    public String loginMain() {
        return "/auth/member";
    }

    @GetMapping("/join")
    public String join() {
        return "/auth/join";
    } 

    @GetMapping("/login")
    public String login() {
        return "/auth/login";
    }

    @GetMapping("/findId")
    public String findId() {
        return "/auth/findId";
    }

    @GetMapping("/findPw")
    public String findPw() {
        return "/auth/findPw";
    }

    @GetMapping("/joinDone")
    public String joinDone() {
        return "/auth/joinDone";
    }


}
