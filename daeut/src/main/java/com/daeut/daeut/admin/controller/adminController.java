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


    @GetMapping("/adminUser")
    public String adminUser() {
        return "/admin/adminUser";
    }


    @GetMapping("/adminPartner")
    public String adminPartner() {
        return "/admin/adminPartner";
    }


    @GetMapping("/adminReservationRead")
    public String adminReservationRead() {
        return "/admin/adminReservationRead";
    }

    @GetMapping("/adminReservationUpdate")
    public String adminReservationUpdate() {
        return "/admin/adminReservationUpdate";
    }

    @GetMapping("/adminUserRead")
    public String adminUserRead() {
        return "/admin/adminUserRead";
    }

    @GetMapping("/adminUserUpdate")
    public String adminUserUpdate() {
        return "/admin/adminUserUpdate";
    }
    @GetMapping("/adminPartnerRead")
    public String adminPartnerRead() {
        return "/admin/adminPartnerRead";
    }
    @GetMapping("/adminPartnerUpdate")
    public String adminPartnerUpdate() {
        return "/admin/adminPartnerUpdate";

    }
}