package com.daeut.daeut.partner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequestMapping("/partner")
public class PartnerController {
    
    @GetMapping("/partnerMypage")
    public String partnerMypage() {
        log.info("[partner] - /partnerMypage");

        return "/partner/partnerMypage";
    }

    @GetMapping("/partnerMypageUpdate")
    public String partnerMypageUpdate() {
        log.info("[partner] - /partnerMypageUpdate");

        return "/partner/partnerMypageUpdate";
    }

    @GetMapping("/partnerReservation")
    public String partnerReservation() {
        log.info("[partner] - /partnerReservation");

        return "/partner/partnerReservation";
    }

    @GetMapping("/partnerReservationRead")
    public String partnerReservationRead() {
        log.info("[partner] - /partnerReservationRead");

        return "/partner/partnerReservationRead";
    }

    @GetMapping("/partnerReview")
    public String partnerReview() {
        log.info("[partner] - /partnerReview");

        return "/partner/partnerReview";
    }
}
