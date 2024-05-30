package com.daeut.daeut.auth.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.auth.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/member")
    public String loginMain() {
        return "/auth/member";
    }

    @GetMapping("/join")
    public String join() {
        return "/auth/join";
    }

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

    @GetMapping("/findId")
    public String findId() {
        return "/auth/findId";
    }

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

    @GetMapping("/findIdComplete")
    public String findIdComplete(@RequestParam String userId, Model model) {
        model.addAttribute("userId", userId);
        return "/auth/findIdComplete";
    }

    @GetMapping("/findPw")
    public String findPw() {
        return "/auth/findPw";
    }

    @GetMapping("/joinDone")
    public String joinDone() {
        return "/auth/joinDone";
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

    // 로그인 처리
    @PostMapping("/login")
    public String loginUser(@RequestParam String userId, @RequestParam String userPassword, Model model) {
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
}
