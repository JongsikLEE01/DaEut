package com.daeut.daeut.auth.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.daeut.daeut.auth.dto.CustomUser;
import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.auth.mapper.UserMapper;
import com.daeut.daeut.partner.dto.Partner;
import com.daeut.daeut.partner.service.PartnerService;
import com.nimbusds.jose.crypto.impl.CipherHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * ✅ 로그인 성공 처리 클래스
 */
@Slf4j
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    
    @Autowired
    private PartnerService partnerService;

    @Autowired
    private UserMapper userMapper;

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
    
    
        // 인증된 사용자 정보 - (아이디/패스워드/권한)
        // User user = (User) authentication.getPrincipal();
        log.info("::::::::::::::::::::::::::::::::::::::::::");
        log.info("authentication : " + authentication);

        CustomUser customUser = null;
        // 소셜 로그인 
        if ( authentication instanceof OAuth2AuthenticationToken ) {
            // authentication.getName() : user 테이블의 user_id
            Users user = new Users();
            try {
                user = userMapper.select(authentication.getName() );
            } catch (Exception e) {
                e.printStackTrace();
            }
            user.setUserId(authentication.getName());
            customUser = new CustomUser(user);
        }
        // 그냥 로그인
        else {
            customUser = (CustomUser) authentication.getPrincipal();
            log.info("아이디 : " + customUser.getUsername());
            log.info("패스워드 : " + customUser.getPassword());       // 보안상 노출❌
            log.info("권한 : " + customUser.getAuthorities());    
        }

    
        HttpSession session = request.getSession();
        Users user = customUser.getUser();
        Partner partner = null; // partner 변수를 선언하고 null로 초기화
        if( user != null ) {
            log.info(":::::::::::::::::::::::::::::::::::::::::::::");
            log.info("userNo : " + user.getUserNo());
            session.setAttribute("user", user);
            session.setAttribute("userNo", user.getUserNo());
    
            try {
                partner = partnerService.findByUserNo(user.getUserNo());
                // 로그로 유저 번호 출력
                log.info("유저 번호 : " + user.getUserNo());
            } catch (Exception e) {
                log.error("파트너 정보를 가져오는 도중 오류가 발생했습니다.", e);
            }
            if(partner != null) {
                session.setAttribute("partnerNo", partner.getPartnerNo());
                session.setAttribute("userNo", partner.getUserNo());
                log.info("파트너 번호 {}가 세션에 저장되었습니다.", partner.getPartnerNo());
                log.info("파트너의 유저번호 {}가 세션에 저장되었습니다.", partner.getUserNo());
            }
        }
        super.onAuthenticationSuccess(request, response, authentication);
    
    }
    
}
