package com.daeut.daeut.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class adminController {

    @GetMapping("/adminReservation")
    public String adminReservation() {
        return "/admin/adminReservation";
    }
}
