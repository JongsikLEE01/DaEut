package com.daeut.daeut.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("")
    public String index() {
        return "/index";
    }
    
    @GetMapping("/payment")
    public String payment() {
        return "/reservation/payment";
    } 
}