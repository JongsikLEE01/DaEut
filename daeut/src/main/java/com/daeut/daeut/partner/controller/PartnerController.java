package com.daeut.daeut.partner.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.auth.service.UserService;
import com.daeut.daeut.partner.dto.Partner;
import com.daeut.daeut.partner.dto.Review;
import com.daeut.daeut.partner.service.PartnerService;
import com.daeut.daeut.reservation.dto.Orders;
import com.daeut.daeut.reservation.service.OrderService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;



@Slf4j
@Controller
@RequestMapping("/partner")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    
    
    // 마이페이지 정보 조회
    @GetMapping("/partnerMypage")
    public String partnerMypage(Model model, HttpSession session) throws Exception {
        try {
            Users user = (Users) session.getAttribute("user");
            if (user == null) {
                log.error("User not found in session");
                throw new Exception("User not found in session");
            }
    
            int userNo = user.getUserNo();
            log.info("Fetching partner information for userNo: {}", userNo);
    
            Partner partner = partnerService.getPartners(userNo);
    
            if (partner == null) {
                log.warn("Partner 객체가 null입니다 for userNo: {}", userNo);
            } else {
                log.info("Partner 객체: {}", partner.toString());
            }
    
            model.addAttribute("user", user);
            model.addAttribute("partner", partner);
    
            log.info("Partner my page accessed by user: {}", user.toString());
    
            return "/partner/partnerMypage";
        } catch (Exception e) {
            log.error("Error in partnerMypage method", e);
            throw e;
        }
    }
    

    // 마이 페이지 수정 화면 조회
    @GetMapping("/partnerMypageUpdate")
    @PreAuthorize("hasRole('ROLE_PARTNER')")
    public String partnerMypageUpdate(Model model, HttpSession session, @RequestParam("userNo") int userNo) throws Exception {
        try {
            Users user = (Users) session.getAttribute("user");
            if (user == null) {
                log.error("User not found in session");
                throw new Exception("User not found in session");
            }
    
            Partner partner = partnerService.getPartners(userNo);
    
            if (partner == null) {
                log.warn("Partner 객체가 null입니다 for userNo: {}", userNo);
            } else {
                log.info("Partner 객체: {}", partner.toString());
            }
    
            model.addAttribute("user", user);
            model.addAttribute("partner", partner);
    
            log.info("Partner my page accessed by user: {}", user.toString());
    
            return "partner/partnerMypageUpdate";
        } catch (Exception e) {
            log.error("Error in partnerMypageUpdate method", e);
            throw e;
        }
    }

    // 수정 처리
    @PostMapping("/partnerMypageUpdatePro")
    public String partnerMypageUpdatePro(Model model, HttpSession session, @ModelAttribute("user") Users user, @ModelAttribute("partner") Partner partner) throws Exception {
        int result = partnerService.partnerUpdate(partner, user);
    
        if (result > 0) {
            return "redirect:/partner/partnerMypage";
        } else {
            return "redirect:/index";
        }
    }

    // 탈퇴 처리
    @PostMapping("/deleteUser")
public String deleteUser(@RequestParam("userNo") int userNo, @RequestParam("userId") String userId, RedirectAttributes redirectAttributes) {
    try {
        // 사용자 삭제 처리
        Users user = new Users();
        user.setUserNo(userNo);
        user.setUserId(userId);
        int result = userService.delete(user);

        // 로그아웃 처리
        if (result > 0) {
            // SecurityContextHolder를 사용하여 현재 사용자의 인증 정보를 제거
            SecurityContextHolder.clearContext();
            return "redirect:/index"; // 회원 탈퇴 및 로그아웃 성공 시 리다이렉트
        } else {
            // 회원 탈퇴 중 오류 발생 시 메시지 추가
            redirectAttributes.addFlashAttribute("message", "회원 탈퇴 중 오류가 발생했습니다.");
        }
    } catch (Exception e) {
        // 예외 발생 시 메시지 추가
        e.printStackTrace();
        redirectAttributes.addFlashAttribute("message", "예외가 발생했습니다: " + e.getMessage());
    }
    return "redirect:/index"; // 회원 탈퇴 및 로그아웃 실패 시 리다이렉트
}


    
    



    // 파트너 리뷰란
    @GetMapping("/partnerReview")
    @Transactional
    public String getReviewsByPartnerNo(Model model, HttpSession session) throws Exception {
        try {
            Integer partnerNo = (Integer) session.getAttribute("partnerNo");
            if (partnerNo == null) {
                log.error("PartnerNo not found in session");
                throw new Exception("PartnerNo not found in session");
            }
            
            List<Review> reviews = partnerService.getReviews(partnerNo);
            
            // Add reviews to the log
            log.info("Reviews retrieved: {}", reviews);
            
            model.addAttribute("reviews", reviews);
            
            return "/partner/partnerReview";
        } catch (Exception e) {
            log.error("Error in getReviewsByPartnerNo method", e);
            throw e;
        }
    }


    // 파트너 예약란
    @GetMapping("/partnerReservation")
    public String partnerReservation(Model model, HttpSession session) throws Exception {
          int partnerNo = (int) session.getAttribute("partnerNo"); // 세션에서 partnerNo 가져오기
        List<Orders> orderList = orderService.listByParterNo(partnerNo); // 주문 목록 가져오기
        model.addAttribute("orderList", orderList); // 모델에 주문 목록 추가
        return "/partner/partnerReservation";  
    }

    // 파트너 예약 상세조회란
    @GetMapping("/partnerReservationRead")
    public String partnerReservationRead(@RequestParam("ordersNo") String ordersNo, Model model) throws Exception {
        log.info("[partner] - /partnerReservationRead");
    
        // 주문에 대한 상세 정보를 조회하고 모델에 추가
        Orders order = orderService.listByOrderNo(ordersNo);
    
        model.addAttribute("order", order);
    
        return "/partner/partnerReservationRead";
    }
}
