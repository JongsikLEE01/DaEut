package com.daeut.daeut.partner.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daeut.daeut.partner.dto.Partner;
import com.daeut.daeut.partner.service.PartnerService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@Controller
@RequestMapping("/partner")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;
    
    // 마이페이지 정보 조회
    @GetMapping("/partnerMypage")
    public String partnerMypage(HttpSession session, Model model) {
    log.info("[partner] - /partnerMypage");

    // 세션에서 로그인한 사용자 정보 가져오기
    User loginUser = (User) session.getAttribute("loginUser");

    // 가져온 사용자 정보를 모델에 추가하여 뷰로 전달
    model.addAttribute("loginUser", loginUser);

    return "/partner/partnerMypage";
}
    // @GetMapping("/partnerMypage")
    // public String partnerMypage(@RequestParam("parnterNo") int partnerNo, Model model) throws Exception {
    //     log.info("[partner] - /partnerMypage");
    //     // 데이터 요청
    //     Partner partner = partnerService.select(partnerNo);
    //     model.addAttribute("partner", partner);
    //     return "/partner/partnerMypage";
    // }

    // @GetMapping("/partnerMypage")
    // public String partnerMypage() {
    //     return "/partner/partnerMypage";
    // }
    

    // 마이 페이지 수정(업데이트)
    @GetMapping("/partnerMypageUpdate")
    public String partnerMypageUpdate(Model model, @RequestParam("partnerNo") int partnerNo) throws Exception {
        log.info("[partner] - /partnerMypageUpdate");
        Partner partner = partnerService.select(partnerNo);
        model.addAttribute("partner", partner);
        return "/partner/partnerMypageUpdate";
    }

    // 마이 페이지 수정(ing)
    @PostMapping("/partnerMypageUpdate")
    public String partnerMypageUpdatePro(Partner partner) throws Exception {
        int result = partnerService.update(partner);
        if (result >0) {
            return "redirect:/partner/partnerMypage";
        }
        return "redirect:/partnerMypageUpdate?partnerno =" + "&error";
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
