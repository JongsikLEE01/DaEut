package com.daeut.daeut.auth.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.auth.service.EmailService;
import com.daeut.daeut.auth.service.UserService;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;


    // 로그인 선택
    @GetMapping("/member")
    public String loginMain() {
        return "/auth/member";
    }

    // 회원가입 화면
    @GetMapping("/join")
    public String join() {
        return "/auth/join";
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
    public String saveUser(@ModelAttribute Users user, Model model) {
        log.info("회원가입 시작");
        try {
            if (userService.select(user.getUserId()) != null) {
                model.addAttribute("errorMessage", "이미 사용 중인 아이디입니다.");
                log.info("아이디 중복");
                return "/auth/join";
            }

            userService.join(user);
            log.info("회원가입 성공");
            return "redirect:/auth/joinDone";
        } catch (Exception e) {
            log.error("회원가입 중 오류가 발생했습니다.", e);
            model.addAttribute("errorMessage", "회원가입 중 오류가 발생했습니다.");
            return "/auth/join";
        }
    }

    // 회원가입 완료
    @GetMapping("/joinDone")
    public String joinDone() {
        return "/auth/joinDone";
    }

    // 로그인 화면
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @CookieValue(value = "remember-id", required = false) Cookie cookie,
                        Model model
                        ) {
        if (error != null) {
            model.addAttribute("errorMessage", "아이디 또는 비밀번호가 올바르지 않습니다.");
        }

        String userId = "";
        boolean rememberId = false;

        if( cookie != null ) {
            log.info("CookieName : " + cookie.getName());
            log.info("CookieValue : " + cookie.getValue());
            userId = cookie.getValue();
            rememberId = true;
        }

        model.addAttribute("userId", userId);
        model.addAttribute("rememberId", rememberId);

        return "/auth/login";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String loginUser(@RequestParam String userId, @RequestParam String userPassword, HttpSession session, Model model) {
        try {
            Users user = userService.select(userId);
            if (user == null || !new BCryptPasswordEncoder().matches(userPassword, user.getUserPassword())) {
                model.addAttribute("errorMessage", "아이디 또는 비밀번호가 올바르지 않습니다.");
                return "/auth/login";
            }
            // 로그인 성공 처리 (예: 세션에 사용자 정보 저장)
            return "redirect:/";
        } catch (Exception e) {
            log.error("로그인 중 오류가 발생했습니다.", e);
            model.addAttribute("errorMessage", "로그인 중 오류가 발생했습니다.");
            return "/auth/login";
        }
    }

    // 아이디 찾기 화면
    @GetMapping("/findId")
    public String findId() {
        return "/auth/findId";
    }

    // 아이디 찾기 처리
    @PostMapping("/findId")
    public String findId(@RequestParam String userName, 
                        @RequestParam String userEmail, 
                        @RequestParam String userPhone, 
                        Model model) {
        try {
            String userId = userService.findUserByDetails(userName, userEmail, userPhone);
            if (userId != null) {
                model.addAttribute("userId", userId);
                return "redirect:/auth/findIdComplete?userId=" + userId;
            } else {
                model.addAttribute("errorMessage", "일치하는 사용자 정보를 찾을 수 없습니다.");
                return "/auth/findId";
            }
        } catch (Exception e) {
            log.error("아이디 찾기 중 오류가 발생했습니다.", e);
            model.addAttribute("errorMessage", "아이디 찾기 중 오류가 발생했습니다.");
            return "/auth/findId";
        }
    }

    // 아이디 찾기 완료
    @GetMapping("/findIdComplete")
    public String findIdComplete(@RequestParam String userId, Model model) {
        model.addAttribute("userId", userId);
        return "/auth/findIdComplete";
    }

    // 비밀번호 찾기 화면
    @GetMapping("/findPw")
    public String findPw() {
        return "/auth/findPw";
    }

    // 비밀번호 찾기 처리
    @PostMapping("/sendAuthCode")
    @ResponseBody
    public String sendAuthCode(@RequestBody Map<String, String> payload, HttpSession session) {
        try {
            String userEmail = payload.get("userEmail");
            String authCode = generateAuthCode();
            session.setAttribute("authCode", authCode);
            session.setAttribute("userEmail", userEmail);

            emailService.sendSimpleMessage(userEmail, "비밀번호 찾기 인증 코드", "인증 코드: " + authCode);
            return "인증 코드가 이메일로 전송되었습니다.";
        } catch (Exception e) {
            log.error("인증 코드 전송 중 오류가 발생했습니다.", e);
            return "인증 코드 전송에 실패했습니다.";
        }
    }

    @PostMapping("/findPw")
    public String findPw(@RequestParam String userName,
                        @RequestParam String userId,
                        @RequestParam String userEmail,
                        @RequestParam String authCode,
                        Model model,
                        HttpSession session) {
        try {
            String sessionAuthCode = (String) session.getAttribute("authCode");
            String sessionUserEmail = (String) session.getAttribute("userEmail");

            if (sessionAuthCode != null && sessionAuthCode.equals(authCode) && userEmail.equals(sessionUserEmail)) {
                return "redirect:/auth/resetPw?userId=" + userId;
            } else {
                model.addAttribute("errorMessage", "인증 코드가 잘못되었거나 유효하지 않습니다.");
                return "/auth/findPw";
            }
        } catch (Exception e) {
            log.error("비밀번호 찾기 중 오류가 발생했습니다.", e);
            model.addAttribute("errorMessage", "비밀번호 찾기 중 오류가 발생했습니다.");
            return "/auth/findPw";
        }
}


    private String generateAuthCode() {
        Random random = new Random();
        int authCode = 100000 + random.nextInt(900000);
        return String.valueOf(authCode);
    }

    // 비밀번호 재설정 화면
    @GetMapping("/resetPw")
    public String resetPw(@RequestParam String userId, Model model) {
        model.addAttribute("userId", userId);
        return "/auth/resetPw";
    }

    // 비밀번호 재설정 처리
    @PostMapping("/resetPw")
    public String resetPw(@RequestParam String userId,
                        @RequestParam String userPassword,
                        @RequestParam String confirmPassword,
                        Model model) {
        try {
            Users user = userService.select(userId);
            if (user != null) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
                if (passwordEncoder.matches(userPassword, user.getUserPassword())) {
                    model.addAttribute("errorMessage", "기존의 비밀번호와 일치합니다.");
                    model.addAttribute("userId", userId);
                    return "/auth/resetPw";
                }
    
                if (!userPassword.equals(confirmPassword)) {
                    model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
                    model.addAttribute("userId", userId);
                    return "/auth/resetPw";
                }
    
                user.setUserPassword(passwordEncoder.encode(userPassword));
                userService.updatePw(user);
                return "redirect:/auth/resetPwComplete";
            } else {
                model.addAttribute("errorMessage", "사용자를 찾을 수 없습니다.");
                return "/auth/resetPw";
            }
        } catch (Exception e) {
            log.error("비밀번호 재설정 중 오류가 발생했습니다.", e);
            model.addAttribute("errorMessage", "비밀번호 재설정 중 오류가 발생했습니다.");
            return "/auth/resetPw";
        }
    }
    

    // 비밀번호 재설저 성공
    @GetMapping("/resetPwComplete")
    public String resetPwComplete() {
        return "/auth/resetPwComplete";
    }

}
