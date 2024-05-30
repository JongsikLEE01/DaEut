package com.daeut.daeut.partner.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daeut.daeut.auth.dto.CustomUser;
import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.partner.dto.Partner;
import com.daeut.daeut.partner.service.PartnerService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.core.annotation.AuthenticationPrincipal;


@Slf4j
@Controller
@RequestMapping("/partner")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;
    
    // 마이페이지 정보 조회
    @GetMapping("/partnerMypage")
    public String partnerMypage(@AuthenticationPrincipal CustomUser customUser, Model model) throws Exception {
        Users user = customUser.getUser();
        
        // 사용자 번호 가져오기
        int userNo = user.getUserNo();
        
        // 사용자의 파트너 정보 가져오기
        List<Partner> partners = partnerService.getPartners(userNo);
        
        // 첫 번째 파트너 정보만 사용
        Partner partner = !partners.isEmpty() ? partners.get(0) : null;
        
        // 모델에 사용자와 파트너 정보 추가
        model.addAttribute("user", user);
        model.addAttribute("partner", partner); // partners 대신 partner를 추가
        
        return "/partner/partnerMypage";
    }
    
    // 마이 페이지 수정(업데이트)
    // @GetMapping("/partnerMypageUpdate")
    // public String partnerMypageUpdate(Model model, @RequestParam("partnerNo") int partnerNo) throws Exception {
    //     log.info("[partner] - /partnerMypageUpdate");
    //     Partner partner = partnerService.select(partnerNo);
    //     model.addAttribute("partner", partner);
    //     return "/partner/partnerMypageUpdate";
    // }

    // 마이 페이지 수정(ing)
    // @PostMapping("/partnerMypageUpdate")
    // public String partnerMypageUpdatePro(Partner partner) throws Exception {
    //     int result = partnerService.update(partner);
    //     if (result >0) {
    //         return "redirect:/partner/partnerMypage";
    //     }
    //     return "redirect:/partnerMypageUpdate?partnerno =" + "&error";
    // }
    

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
