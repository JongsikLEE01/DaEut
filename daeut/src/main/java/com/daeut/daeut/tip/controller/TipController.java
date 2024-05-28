package com.daeut.daeut.tip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Controller
@RequestMapping("/tip")
public class TipController {


    @GetMapping("/Index")
    public String tipHome() {
        log.info("[tip] - /tip");

        return "/tip/Index";
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
    
}
