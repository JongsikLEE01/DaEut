package com.daeut.daeut.partner.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.daeut.daeut.auth.dto.CustomUser;
import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.auth.service.UserService;
import com.daeut.daeut.partner.dto.Partner;
import com.daeut.daeut.partner.service.PartnerService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;


@Slf4j
@Controller
@RequestMapping("/partner")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private UserService userService;

    
    
    // 마이페이지 정보 조회
    @GetMapping("/partnerMypage")
    @PreAuthorize("hasRole('ROLE_PARTNER')")
    public String partnerMypage(@AuthenticationPrincipal CustomUser customUser, Model model) throws Exception {
        Users user = customUser.getUser();
        
        // 사용자 번호 가져오기
        int userNo = user.getUserNo();
        
        // 사용자의 파트너 정보 가져오기
        Partner partner = partnerService.getPartners(userNo);
        
        // 모델에 사용자와 파트너 정보 추가
        model.addAttribute("user", user);
        model.addAttribute("partner", partner);
        
        return "/partner/partnerMypage";
    }

    // @GetMapping("/partnerMypage")
    // @PreAuthorize("hasRole('ROLE_PARTNER')")
    // public String partnerMypage(@AuthenticationPrincipal CustomUser customUser, Model model) throws Exception {
    //     Users user = customUser.getUser();
        
    //     // 사용자 번호 가져오기
    //     int userNo = user.getUserNo();
        
    //     // 사용자의 파트너 정보 가져오기
    //     Partner partner = partnerService.getPartners(userNo); // partnerService를 통해 파트너 정보 조회
        
    //     // 모델에 사용자와 파트너 정보 추가
    //     model.addAttribute("user", user);
    //     model.addAttribute("partner", partner); // 파트너 정보를 모델에 추가
        
    //     return "/partner/partnerMypage";
    // }
    


    // @GetMapping("/partnerMypage")
    // @PreAuthorize("hasRole('ROLE_PARTNER')")
    // public String partnerMypage(@AuthenticationPrincipal CustomUser customUser, Model model) throws Exception {
    //     Users user = customUser.getUser();
        
    //     // 사용자 번호 가져오기
    //     int userNo = user.getUserNo();
        
    //     // 사용자의 파트너 정보 가져오기
    //     List<Partner> partners = partnerService.getPartners(userNo);
        
    //     // 첫 번째 파트너 정보만 사용
    //     Partner partner = !partners.isEmpty() ? partners.get(0) : null;
        
    //     // 모델에 사용자와 파트너 정보 추가
    //     model.addAttribute("user", user);
    //     model.addAttribute("partner", partner); // partners 대신 partner를 추가
        
    //     return "/partner/partnerMypage";
    // }
    
    // 마이 페이지 수정 화면 조회
    @GetMapping("/partnerMypageUpdate")
    @PreAuthorize("hasRole('ROLE_PARTNER')")
    public String partnerMypageUpdate(@AuthenticationPrincipal CustomUser customUser, Model model) throws Exception {
        Users user = customUser.getUser();
        
        // 사용자 번호 가져오기
        int userNo = user.getUserNo();
        
        // 사용자의 파트너 정보 가져오기
        Partner partner = partnerService.getPartners(userNo);
        
        // 모델에 사용자와 파트너 정보 추가
        model.addAttribute("user", user);
        model.addAttribute("partner", partner); // 파트너 정보를 모델에 추가
        
        return "/partner/partnerMypage";
    }

    // 마이 페이지 수정 기능
    @PostMapping("/partnerMypageUpdate")
    @PreAuthorize("hasRole('ROLE_PARTNER')")
    public String partnerMypageUpdate(
        @AuthenticationPrincipal CustomUser customUser,
        @ModelAttribute("partner") @DateTimeFormat(pattern = "yyyy-MM-dd") Partner partner, // 날짜 형식 매핑 추가
        RedirectAttributes redirectAttributes) {

    // 현재 로그인한 사용자의 정보를 가져와 Partner 객체에 설정
    Users user = customUser.getUser();
    partner.setUserNo(user.getUserNo());

    try {
        int result = partnerService.partnerUpdate(partner); // 파트너 객체를 업데이트.
        if (result > 0) {
            redirectAttributes.addFlashAttribute("message", "정보가 성공적으로 수정되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("message", "정보 수정에 실패했습니다.");
        }
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("message", "정보 수정 중 오류가 발생했습니다.");
        e.printStackTrace();
    }

    // 리다이렉션할 페이지 경로를 반환합니다.
    return "redirect:/partner/partnerMypage";
}







//     @PostMapping("/partnerMypageUpdate")
//     @PreAuthorize("hasRole('ROLE_PARTNER')")
//     public String partnerMypageUpdate(
//             @AuthenticationPrincipal CustomUser customUser,
//             @ModelAttribute("partner") Partner partner, // @ModelAttribute 애노테이션을 사용하여 파트너 객체를 가져옵니다.
//             RedirectAttributes redirectAttributes) {

//         // 현재 로그인한 사용자의 정보를 가져와 Partner 객체에 설정
//         Users user = customUser.getUser();
//         partner.setUserNo(user.getUserNo());

//         try {
//             int result = partnerService.partnerUpdate(partner); // 파트너 객체를 업데이트.
//             if (result > 0) {
//                 redirectAttributes.addFlashAttribute("message", "정보가 성공적으로 수정되었습니다.");
//             } else {
//                 redirectAttributes.addFlashAttribute("message", "정보 수정에 실패했습니다.");
//             }
//         } catch (Exception e) {
//             redirectAttributes.addFlashAttribute("message", "정보 수정 중 오류가 발생했습니다.");
//             e.printStackTrace();
//         }

//     // 리다이렉션할 페이지 경로를 반환합니다.
//     return "redirect:/partner/partnerMypage";
// }





    // @PostMapping("/partnerMypageUpdate")
    // @PreAuthorize("hasRole('ROLE_PARTNER')")
    // public String partnerMypageUpdate(
    //         @AuthenticationPrincipal CustomUser customUser,
    //         Partner partner,
    //         RedirectAttributes redirectAttributes) {
    
    //     // 현재 로그인한 사용자의 정보를 가져와 Partner 객체에 설정
    //     Users user = customUser.getUser();
    //     partner.setUserNo(user.getUserNo());
    
    //     try {
    //         int result = partnerService.update(partner);
    //         if (result > 0) {
    //             redirectAttributes.addFlashAttribute("message", "정보가 성공적으로 수정되었습니다.");
    //         } else {
    //             redirectAttributes.addFlashAttribute("message", "정보 수정에 실패했습니다.");
    //         }
    //     } catch (Exception e) {
    //         redirectAttributes.addFlashAttribute("message", "정보 수정 중 오류가 발생했습니다.");
    //         e.printStackTrace();
    //     }
    
    //     // 리다이렉션할 페이지 경로를 반환합니다.
    //     return "redirect:/partner/partnerMypage";
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
