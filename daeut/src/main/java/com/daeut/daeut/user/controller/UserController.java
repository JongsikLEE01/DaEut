package com.daeut.daeut.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping({"/", ""})
    public String index() {
        log.info("/user");
        return "/user/index";
    }

    @GetMapping("/userMypage")
    public String userMypage() {
        log.info("/user/userMypage");
        return "/user/userMypage";
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