package com.daeut.daeut.auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.auth.mapper.UserMapper;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/member")
    public String loginMain() {
        return "/auth/member";
    }

    @GetMapping("/join")
    public String join() {
        return "/auth/join";
    }

    @GetMapping("/login")
    public String login() {
        return "/auth/login";
    }

    @GetMapping("/findId")
    public String findId() {
        return "/auth/findId";
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
        Users user = userMapper.select(userId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", user != null);
        return response;
    }

    // 회원가입 처리
    @PostMapping("/join")
    public String saveUser(@ModelAttribute Users user, Model model) {
        try {
            if (userMapper.select(user.getUserId()) != null) {
                model.addAttribute("errorMessage", "이미 사용 중인 아이디입니다.");
                return "/auth/join";
            }
            // 비밀번호 암호화
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
            
            userMapper.join(user);
            return "redirect:/auth/joinDone";
        } catch (Exception e) {
            logger.error("회원가입 중 오류가 발생했습니다.", e);
            model.addAttribute("errorMessage", "회원가입 중 오류가 발생했습니다.");
            return "/auth/join";
        }
    }

    // 로그인 처리
    @PostMapping("/login")
    public String loginUser(@RequestParam String userId, @RequestParam String userPassword, Model model) {
        try {
            Users user = userMapper.login(userId);
            if (user == null || !new BCryptPasswordEncoder().matches(userPassword, user.getUserPassword())) {
                model.addAttribute("errorMessage", "아이디 또는 비밀번호가 올바르지 않습니다.");
                return "/auth/login";
            }
            // 로그인 성공 처리 (예: 세션에 사용자 정보 저장)
            return "redirect:/";
        } catch (Exception e) {
            logger.error("로그인 중 오류가 발생했습니다.", e);
            model.addAttribute("errorMessage", "로그인 중 오류가 발생했습니다.");
            return "/auth/login";
        }
    }
}
