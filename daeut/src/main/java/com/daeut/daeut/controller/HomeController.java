package com.daeut.daeut.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/admin/adminPartner")
    public String adminPartner() {
        return "/admin/adminPartner";
    }
    @GetMapping("/admin/adminPartnerRead")
    public String adminPartnerRead() {
        return "/admin/adminPartnerRead";
    }
    @GetMapping("/admin/adminPartnerUpdate")
    public String adminPartnerUpdate() {
        return "/admin/adminPartnerUpdate";
    }
}