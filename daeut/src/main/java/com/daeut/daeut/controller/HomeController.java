package com.daeut.daeut.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("")
public class HomeController {

    @GetMapping("/index")
    public String index() {
        return "/index";
    }
    
}