package com.daeut.daeut.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.auth.service.UserService;
import com.daeut.daeut.main.dto.Page;
import com.daeut.daeut.partner.dto.Partner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Value("${system.pw}")
    private String systemPw;

    @Autowired
    private UserService userService;

    //  @Autowired
    // private PasswordEncoder passwordEncoder;


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
    public String adminUser(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber) throws Exception {
        int total = userService.countUsers(); // 총 사용자 수 계산
        Page page = new Page(pageNumber, total); // Page 객체 초기화
        List<Users> userList = userService.selectAllUsers(page);
        model.addAttribute("userList", userList);
        model.addAttribute("page", page);
        return "/admin/adminUser";
    }
    // @GetMapping("/adminPartner")
    // public String adminPartner(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber) throws Exception {
    //     int total = userService.countPartners(); // 총 사용자 수 계산
    //     Page page = new Page(pageNumber, total); // Page 객체 초기화
    //     List<Partner> partnerList = userService.selectAllPartners(page);
    //     model.addAttribute("partnerList", partnerList);
    //     model.addAttribute("page", page);
    //     return "/admin/adminPartner";
    // }


    @GetMapping("/adminReservationRead")
    public String adminReservationRead() {
        return "/admin/adminReservationRead";
    }

    @GetMapping("/adminReservationUpdate")
    public String adminReservationUpdate() {
        return "/admin/adminReservationUpdate";
    }

    @GetMapping("/adminUserRead/{userNo}")
    public String adminUserRead(@PathVariable("userNo") int userNo, Model model) throws Exception {
        Users user = userService.findUserById(userNo);
        log.info(user.toString());
        model.addAttribute("user", user);
        return "/admin/adminUserRead";
    }
    @GetMapping("/adminUserUpdate/{userNo}")
    public String adminUserUpdate(@PathVariable("userNo") int userNo, Model model) throws Exception {
        Users user = userService.findUserById(userNo);
        model.addAttribute("user", user);
        log.info("업데이트 화면이동...");
        log.info(user.toString());
        return "/admin/adminUserUpdate";
    }
    @PostMapping("/adminUserUpdate/{userNo}")
    public String adminUserUpdatePro(Users user, @RequestParam("userNo") int userNo, Model model) throws Exception {
        Users existingUser = userService.findUserById(userNo);
        // String newPassword = user.getUserPassword();

        // // 비밀번호가 입력된 경우에만 처리
        // if (newPassword != null && !newPassword.isEmpty()) {
        //     // 기존 비밀번호와 동일한지 확인
        //     if (passwordEncoder.matches(newPassword, existingUser.getUserPassword())) {
        //         model.addAttribute("error", "새 비밀번호가 기존 비밀번호와 동일합니다.");
        //         model.addAttribute("user", existingUser); // 기존 사용자 정보를 다시 전달
        //         return "admin/adminUserUpdate"; // 동일한 페이지로 다시 이동
        //     }
        //     // 새 비밀번호 암호화
        //     String encodedPassword = passwordEncoder.encode(newPassword);
        //     user.setUserPassword(encodedPassword);
        // } else {
        //     user.setUserPassword(existingUser.getUserPassword());
        // }

        int result = userService.adminUpdateUser(user);
        log.info("회원 수정 중.....");
        int no = user.getUserNo();
        if (result > 0) {
            return "redirect:/admin/adminUserRead/" + no;
        }
        model.addAttribute("error", "사용자 업데이트에 실패했습니다.");
        model.addAttribute("user", existingUser); // 기존 사용자 정보를 다시 전달
        return "admin/adminUserUpdate";
    }
    @PostMapping("/adminUserDelete")
    public String adminUserDelete(@RequestParam("userNo") int userNo, Model model) throws Exception {
        int result = userService.adminDeleteUser(userNo);
        if (result > 0) {
            return "redirect:/admin/adminUser";
        }
        model.addAttribute("error", "사용자 삭제에 실패했습니다.");
        Users user = userService.findUserById(userNo); // 삭제 실패 시 사용자 정보를 다시 가져와서 모델에 추가
        model.addAttribute("user", user);
        return "admin/adminUserUpdate";
    }
    

    // @GetMapping("/adminPartnerRead/{userNo}")
    // public String adminPartnerRead(@PathVariable("userNo") int userNo, Model model) throws Exception {
    //     Partner partner = userService.findPartnerById(userNo);
    //     log.info(partner.toString());
    //     model.addAttribute("partner", partner);
    //     return "/admin/adminPartnerRead";
    // }
    // @GetMapping("/adminPartnerUpdate/{userNo}")
    // public String adminPartnerUpdate(@PathVariable("userNo") int userNo, Model model) throws Exception {
    //     Partner partner = userService.findPartnerById(userNo);
    //     model.addAttribute("partner", partner);
    //     log.info("업데이트 화면 이동....");
    //     log.info(partner.toString());
    //     return "/admin/adminPartnerUpdate";

    // }
    // @PostMapping("/adminPartnerUpdate/{userNo}")
    // public String adminPartnerUpdatePro(Partner partner, @RequestParam("userNo") int userNo, Model model) throws Exception {
    //     Partner existingUser = userService.findPartnerById(userNo);
    //     int result = userService.adminUpdatePartner(partner);
    //     log.info("회원 수정 중..... result: " + result);
    //     int no = partner.getUserNo();
    //     if (result > 0) {
    //         return "redirect:/admin/adminPartnerRead/" + no;
    //     }
    //     model.addAttribute("error", "사용자 업데이트에 실패했습니다.");
    //     model.addAttribute("partner", existingUser); // 기존 사용자 정보를 다시 전달
    //     return "admin/adminPartnerUpdate";
    // }


    @PostMapping("/user/delete")
    public String selectedUserDelete(String[] deleteNoList) throws Exception {
        log.info(":::::::::: 선택한 유저 번호들 ::::::::::");
        log.info("deleteNoList 개수 : " + deleteNoList.length);
        for (String no : deleteNoList) {
            log.info("no  : " + no);
        }
        int result = userService.deleteList(deleteNoList);
        log.info("삭제된 회원 수 : " + result);

        return "redirect:/admin/adminUser";
    }

    @PostMapping("/partner/delete")
    public String selectedPartnerDelete(String[] deleteNoList) throws Exception {
        log.info(":::::::::: 선택한 유저 번호들 ::::::::::");
        log.info("deleteNoList 개수 : " + deleteNoList.length);
        for (String no : deleteNoList) {
            log.info("no  : " + no);
        }
        int result = userService.deleteList(deleteNoList);
        log.info("삭제된 회원 수 : " + result);

        return "redirect:/admin/adminPartner";
    }

    
}   