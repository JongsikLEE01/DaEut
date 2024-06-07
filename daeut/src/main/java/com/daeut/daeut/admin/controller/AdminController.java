package com.daeut.daeut.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daeut.daeut.admin.service.AdminService;
import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.auth.service.UserService;
import com.daeut.daeut.main.dto.Page;
import com.daeut.daeut.partner.dto.Partner;
import com.daeut.daeut.reservation.dto.Orders;
import com.daeut.daeut.reservation.dto.Payments;
import com.daeut.daeut.reservation.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;




@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Value("${system.pw}")
    private String systemPw;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private PaymentService paymentService;

    //  @Autowired
    // private PasswordEncoder passwordEncoder;

    // 회원가입 화면
    @GetMapping("/join")
    public String join() {
        return "/admin/join";
    }

    // 아이디 중복 확인
    @GetMapping("/check-duplicate")
    @ResponseBody
    public Map<String, Boolean> checkDuplicateId(@RequestParam String userId) throws Exception {
        Users user = userService.select(userId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", user != null);
        return response;
    }

    // 이메일 중복 확인
    @GetMapping("/check-duplicate-email")
    @ResponseBody
    public Map<String, Boolean> checkDuplicateEmail(@RequestParam String userEmail) throws Exception {
        Users user = userService.findUserByEmail(userEmail);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", user != null);
        return response;
    }

    // 회원가입 처리
    @PostMapping("/join")
    public String adminJoin(@ModelAttribute Users user, @RequestParam String systemPw, Model model) {
        try {
            if (userService.select(user.getUserId()) != null) {
                model.addAttribute("errorMessage", "이미 사용 중인 아이디입니다.");
                log.info("아이디 중복");
                return "/admin/join";
            }

            adminService.adminJoin(user, systemPw);
            return "redirect:/admin/joinDone";
        } catch (Exception e) {
            log.error("회원가입 중 오류가 발생했습니다.", e);
            model.addAttribute("errorMessage", "회원가입 중 오류가 발생했습니다.");
            return "/admin/join";
        }
    }

    // 회원가입 완료
    @GetMapping("/joinDone")
    public String joinDone() {
        return "/admin/joinDone";
    }

    // 관리자 - 회원 목록
    @GetMapping("/adminUser")
    public String adminUser(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber) throws Exception {
        int total = adminService.countUsers(); // 총 사용자 수 계산
        Page page = new Page(pageNumber, total); // Page 객체 초기화
        List<Users> userList = adminService.selectAllUsers(page);
        model.addAttribute("userList", userList);
        model.addAttribute("page", page);
        return "/admin/adminUser";
    }

    // 관리자 - 파트너 목록
    @GetMapping("/adminPartner")
    public String adminPartner(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber) throws Exception {
        int total = adminService.countPartners(); // 총 사용자 수 계산
        Page page = new Page(pageNumber, total); // Page 객체 초기화
        List<Partner> partnerList = adminService.selectAllPartners(page);
        model.addAttribute("partnerList", partnerList);
        model.addAttribute("page", page);
        return "/admin/adminPartner"; 
    }

    // 관리자 - 회원 조회
    @GetMapping("/adminUserRead/{userNo}")
    public String adminUserRead(@PathVariable("userNo") int userNo, Model model) throws Exception {
        Users user = adminService.findUserById(userNo);
        log.info(user.toString());
        model.addAttribute("user", user);
        return "/admin/adminUserRead";
    }

    // 관리자 - 회원 수정 화면
    @GetMapping("/adminUserUpdate/{userNo}")
    public String adminUserUpdate(@PathVariable("userNo") int userNo, Model model) throws Exception {
        Users user = adminService.findUserById(userNo);
        model.addAttribute("user", user);
        log.info("업데이트 화면이동...");
        log.info(user.toString());
        return "/admin/adminUserUpdate";
    }

    // 관리자 - 회원 수정 처리
    @PostMapping("/adminUserUpdate/{userNo}")
    public String adminUserUpdatePro(Users user, @RequestParam("userNo") int userNo, Model model) throws Exception {
        Users existingUser = adminService.findUserById(userNo);
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

        int result = adminService.adminUpdateUser(user);
        log.info("회원 수정 중.....");
        int no = user.getUserNo();
        if (result > 0) {
            return "redirect:/admin/adminUserRead/" + no;
        }
        model.addAttribute("error", "사용자 업데이트에 실패했습니다.");
        model.addAttribute("user", existingUser); // 기존 사용자 정보를 다시 전달
        return "/admin/adminUserUpdate";
    }

    // 관리자 - 회원 삭제 처리
    @PostMapping("/adminUserDelete")
    public String adminUserDelete(@RequestParam("userNo") int userNo, Model model) throws Exception {
        int result = adminService.adminDeleteUser(userNo);
        if (result > 0) {
            return "redirect:/admin/adminUser";
        }
        model.addAttribute("error", "사용자 삭제에 실패했습니다.");
        Users user = adminService.findUserById(userNo); // 삭제 실패 시 사용자 정보를 다시 가져와서 모델에 추가
        model.addAttribute("user", user);
        return "/admin/adminUserUpdate";
    }
    
   

    // 관리자 - 파트너 조회 화면
    @GetMapping("/adminPartnerRead/{userNo}")
    public String adminPartnerRead(@PathVariable("userNo") int userNo, Model model) throws Exception {
        Partner partner = adminService.findPartnerById(userNo);
        log.info(partner.toString());
        model.addAttribute("partner", partner);
        return "/admin/adminPartnerRead";
    }

    // 파트너 승인 처리
    @PostMapping("/approvePartner/{userId}")
    public String approvePartner(@PathVariable("userId") String userId) {
        try {
            log.info("파트너 아이디::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: : " + userId);
            adminService.approvePartner(userId);
            adminService.insertPartnerAuth(userId);
            return "redirect:/admin/adminPartner"; // 파트너 목록으로 리다이렉트
        } catch (Exception e) {
            log.error("파트너 승인 중 오류가 발생했습니다.", e);
            // 오류 발생 시 처리
            return "redirect:/admin/adminPartnerRead"; // 오류 페이지로 리다이렉트 또는 다른 방법으로 처리
        }
    }

    // 파트너 승인 취소 처리
    @PostMapping("/cancelPartner/{userId}")
    public String cancelPartner(@PathVariable("userId") String userId) {
        try {
            log.info("파트너 아이디 : " + userId);
            adminService.cancelPartner(userId);
            adminService.deletePartnerAuth(userId);
            return "redirect:/admin/adminPartner"; // 파트너 목록으로 리다이렉트
        } catch (Exception e) {
            log.error("파트너 승인 취소 중 오류가 발생했습니다.", e);
            // 오류 발생 시 처리
            return "redirect:/admin/adminPartner"; // 오류 페이지로 리다이렉트 또는 다른 방법으로 처리
        }
    }
    

    // 관리자 - 파트너 수정 화면 
    @GetMapping("/adminPartnerUpdate/{userNo}")
    public String adminPartnerUpdate(@PathVariable("userNo") int userNo, Model model) throws Exception {
        Partner partner = adminService.findPartnerById(userNo);
        model.addAttribute("partner", partner);
        log.info("업데이트 화면 이동....");
        log.info(partner.toString());
        return "/admin/adminPartnerUpdate";

    }

    // 관리자 - 파트너 수정 처리
    @PostMapping("/adminPartnerUpdate/{userNo}")
    public String adminPartnerUpdatePro(Partner partner, @RequestParam("userNo") int userNo, Model model) throws Exception {
        Partner existingUser = adminService.findPartnerById(userNo);
        int result = adminService.adminUpdatePartner(partner);
        log.info("회원 수정 중..... result: " + result);
        int no = partner.getUserNo();
        if (result > 0) {
            return "redirect:/admin/adminPartnerRead/" + no; // 업데이트 후에 파트너 조회 페이지로 리다이렉트
        }
        model.addAttribute("error", "사용자 업데이트에 실패했습니다.");
        model.addAttribute("partner", existingUser); // 기존 사용자 정보를 다시 전달
        return "/admin/adminPartnerUpdate";
    }

    // 관리자 - 파트너 삭제 처리
    @PostMapping("/adminPartnerDelete/{userNo}")
    public String adminPartnerDelete(@PathVariable("userNo") int userNo, Model model) throws Exception {
        int result = adminService.adminDeletePartner(userNo);
        if (result > 0) {
            return "redirect:/admin/adminPartner";
        }
        model.addAttribute("error", "사용자 삭제에 실패했습니다.");
        Partner partner = adminService.findPartnerById(userNo); // 삭제 실패 시 사용자 정보를 다시 가져와서 모델에 추가
        model.addAttribute("partner", partner);
        return "/admin/adminPartnerUpdate";
    }

    // 관리자 - 회원 선택 삭제 
    @PostMapping("/user/delete")
    public String selectedUserDelete(String[] deleteNoList) throws Exception {
        log.info(":::::::::: 선택한 유저 번호들 ::::::::::");
        log.info("deleteNoList 개수 : " + deleteNoList.length);
        for (String no : deleteNoList) {
            log.info("no  : " + no);
        }
        int result = adminService.deleteList(deleteNoList);
        log.info("삭제된 회원 수 : " + result);

        return "redirect:/admin/adminUser";
    }

    // 관리자 - 파트너 선택 삭제 
    @PostMapping("/partner/delete")
    public String selectedPartnerDelete(String[] deleteNoList) throws Exception {
        log.info(":::::::::: 선택한 유저 번호들 ::::::::::");
        log.info("deleteNoList 개수 : " + deleteNoList.length);
        for (String no : deleteNoList) {
            log.info("no  : " + no);
        }
        int result = adminService.deleteList(deleteNoList);
        log.info("삭제된 회원 수 : " + result);

        return "redirect:/admin/adminPartner";
    }

    // 관리자 - 예약 목록 화면
    @GetMapping("/adminReservation")
    public String selectReservations(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber) throws Exception {
        int total = adminService.countReservations(); // 총 예약 수 계산
        Page page = new Page(pageNumber, total); // Page 객체 초기화
        List<Orders> orderList = adminService.list(page);
        log.info("--------------------------orderList " + orderList);
        model.addAttribute("orderList", orderList);
        model.addAttribute("page", page);
        return "/admin/adminReservation"; 
    }


    // 관리자 - 예약 조회 화면
    @GetMapping("/adminReservationRead")
    public String adminReadReservation(@RequestParam("ordersNo") String ordersNo, Model model) {
        try {
            Orders orders = adminService.adminReadReservation(ordersNo);
            log.info("-------------------------------------------" + ordersNo);
            model.addAttribute("orders", orders);
            log.info(":::::::::::::::::::::::::::::::::::::orders:::::::::::::::::::::::::::::::::::" + orders);
        } catch (Exception e) {
            model.addAttribute("error", "예약 정보를 불러오는 중 오류가 발생했습니다.");
        }
        return "/admin/adminReservationRead";
    }
   

    // 관리자 - 예약 수정 화면
    @GetMapping("/adminReservationUpdate/{ordersNo}")
    public String adminReservationUpdate(@PathVariable("ordersNo") String ordersNo, Model model) throws Exception  {
        try {
            Orders orders = adminService.adminReadReservation(ordersNo);
            model.addAttribute("orders", orders);
            Payments payments = paymentService.selectByOrdersNo(ordersNo);
            model.addAttribute("payments", payments);
            log.info(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::" + orders);
            return "admin/adminReservationUpdate"; // 경로 이름을 정확히 맞추세요.
        } catch (Exception e) {
            log.error("예약 조회 중 오류가 발생했습니다.", e);
            model.addAttribute("error", "예약 조회 중 오류가 발생했습니다.");
            return "redirect:/admin/adminReservation"; // 잘못된 경로로 리다이렉트되지 않도록 합니다.
        }
    }



    // 관리자 - 예약 수정 처리
    @PostMapping("/adminReservationUpdate/{ordersNo}")
    public String adminReservationUpdatePro(Orders orders, @PathVariable("ordersNo") String ordersNo, Model model) {
        try {
            Orders existingOrders = adminService.adminReadReservation(ordersNo);
            int result = adminService.adminUpdateReservation(orders);
            log.info("예약 수정 중..... result: " + result);
            if (result > 0) {
                return "redirect:/admin/adminReservationRead/" + ordersNo; // 업데이트 후에 예약 조회 페이지로 리다이렉트
            }
            model.addAttribute("error", "예약 업데이트에 실패했습니다.");
            model.addAttribute("orders", existingOrders); // 기존 예약 정보를 다시 전달
            return "/admin/adminReservationUpdate";
        } catch (Exception e) {
            log.error("예약 수정 중 오류가 발생했습니다.", e);
            model.addAttribute("error", "예약 수정 중 오류가 발생했습니다.");
            return "/admin/adminReservationUpdate";
        }
    }

 

    
}   