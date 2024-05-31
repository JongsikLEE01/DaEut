package com.daeut.daeut.auth.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;


import lombok.extern.slf4j.Slf4j;

/**
 * ✅ 로그인 성공 처리 클래스
 */
@Slf4j
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request
                                      , HttpServletResponse response
                                      , Authentication authentication) throws ServletException, IOException {
            
        log.info("로그인 인증 성공...");

        // 아이디 저장
        String rememberId = request.getParameter("remember-id");    // 아이디 저장 여부
        String username = request.getParameter("userId");               // 아이디
        log.info("rememberId : " + rememberId);
        log.info("id : " + username);

        // ✅ 아이디 저장 체크
        if(rememberId != null && rememberId.equals("on")){
            Cookie cookie = new Cookie("remember-id", username);
            cookie.setMaxAge(60 * 60 * 24 * 7);     // 유효기간 : 7일
            cookie.setPath("/");                // 쿠키 적용 경로 지정
            response.addCookie(cookie);             // 응답에 쿠키 등록
        }

        // 🟩 아이디 저장 체크 ❌
        else{
            Cookie cookie = new Cookie("remember-id", "");
            cookie.setMaxAge(0);             // 유효기간 : 만료
            cookie.setPath("/");                // 쿠키 적용 경로 지정
            response.addCookie(cookie);             // 응답에 쿠키 등록
        }

         // 로그인 성공 후 기본 페이지로 리다이렉트
         super.onAuthenticationSuccess(request, response, authentication);

    }
    
}