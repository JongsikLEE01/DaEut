package com.daeut.daeut.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daeut.daeut.auth.dto.CustomUser;
import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.auth.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    // @GetMapping({"/", ""})
    // public String index() {
    //     log.info("/user");
    //     return "/user/index";
    // }
    @Autowired
    private UserService userService;

    @GetMapping("/userMypage")
    public String userMypage(@AuthenticationPrincipal CustomUser customUser, Model model) throws Exception {
        log.info("/user/userMypage");

        Users user = customUser.getUser();
        model.addAttribute("user", user);

        return "/user/userMypage";
    }

    @GetMapping("/userMypageUpdate")
    public String userMypageUpdate() {
        log.info("/user/userMypageUpdate");
        return "/user/userMypageUpdate";
    }

    @GetMapping("/userReservation")
    public String userReservation() {
        log.info("/user/userReservation");
        return "/user/userReservation";
    }
    
    @GetMapping("/userLikeTip")
    public String userLikeTip() {
        log.info("/user/userLikeTip");
        return "/user/userLikeTip";
    }

    @GetMapping("/userReview")
    public String userReview() {
        log.info("/user/userReview");
        return "/user/userReview";
    }

    @GetMapping("/userCoupon")
    public String userCoupon() {
        log.info("/user/userCoupon");
        return "/user/userCoupon";
    }

    @GetMapping("/userPartner")
    public String userPartner() {
        log.info("/user/userPartner");
        return "/user/userPartner";
    }

    @GetMapping("/userPartnerDone")
    public String userPartnerDone() {
        log.info("/user/userPartnerDone");
        return "/user/userPartnerDone";
    }
    
}