package com.daeut.daeut.tip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequestMapping("/tip")
public class TipController {


    @GetMapping("/index")
    public String tipHome() {
        log.info("[tip] - /tip");

        return "/tip/index";
    }
    
    @GetMapping("/tipInsert")
    public String tipInsert() {
        log.info("[tip] - /tipInsert");

        return "/tip/tipInsert";
    }

    @GetMapping("/tipRead")
    public String tipRead() {
        log.info("[tip] - /tipRead");

        return "/tip/tipRead";
    }

    @GetMapping("/tipUpdate")
    public String tipUpdate() {
        log.info("[tip] - /tipUpdate");

        return "/tip/tipUpdate";
    }
}