package com.daeut.daeut.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.auth.service.UserService;


@Controller
@RequestMapping("/admin")
public class adminController {

    @Value("${system.pw}")
    private String systemPw;

    @Autowired
    private UserService userService;


    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("user", new Users());
        return "/admin/join";
    }

    @PostMapping("/join")
    public String adminJoin(@ModelAttribute Users user, @RequestParam String systemPw, Model model) {
        try {
            userService.adminJoin(user, systemPw);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "시스템 비밀번호가 잘못되었습니다.");
            return "admin/join";
        } catch (Exception e) {
            // 기타 예외 처리
            model.addAttribute("error", "알 수 없는 오류가 발생했습니다.");
            return "admin/join";
        }
        return "redirect:/admin/joinDone";
    }
    
    @GetMapping("/joinDone")
    public String joinDone() {
        return "/admin/joinDone";
    }

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