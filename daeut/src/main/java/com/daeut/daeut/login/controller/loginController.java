package com.daeut.daeut.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/oauth")
public class loginController {
    
    @GetMapping("/member")
    public String loginMain() {
        return "/oauth/member";
    }

    @GetMapping("/join")
    public String join() {
        return "/oauth/join";
    } 

    @GetMapping("/login")
    public String login() {
        return "/oauth/login";
    }

    @GetMapping("/findId")
    public String findId() {
        return "/oauth/findId";
    }

    @GetMapping("/findPw")
    public String findPw() {
        return "/oauth/findPw";
    }

    @GetMapping("/joinDone")
    public String joinDone() {
        return "/oauth/joinDone";
    }


}
