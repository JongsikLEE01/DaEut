package com.daeut.daeut.auth.service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daeut.daeut.auth.dto.CustomSocialUser;
import com.daeut.daeut.auth.dto.CustomUser;
import com.daeut.daeut.auth.dto.OAuthAttributes;
import com.daeut.daeut.auth.dto.UserAuth;
import com.daeut.daeut.auth.dto.UserSocial;
import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.auth.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OAuthServiceImpl implements OAuthService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 🎫 loadUser 
     * ✅ provider(공급자:카카오,네이버,구글)로부터 사용자 정보(OAuth2UserRequest)를 
     *     가져와서 OAuth2User 객체로 변환하는 메소드
     * ① 최초 로그인 ➡ 회원 가입
     * ② 로그인 ➡ 정보 갱신
     * 
     * ⭐ 주요 정보
     *      - 공급자 식별 키 (registrationId)
     *      - 사용자 식별 속성명 (userNameAttributeName)
     *      - OAuth 2.0 토큰 속성들 (attributes)
     * ⭐ 프로세스
     *      : 각 provider(공급자)마다 인증 사용자 정보(OAuth2User)에 대한 속성명이 다르기 때문에
     *        이를 일원화한 객체(OAuthAttribute)로 만들고, 최종적으로 OAuth2User 로 변환하여 반환
     *      1️⃣ 주요 정보(registrationId, userNameAttributeName, attributes) 추출
     *      2️⃣ 주요 정보를 인자로 OAuthAttribute 객체 생성
     *      3️⃣ 회원 가입 또는 정보 갱신
     *      4️⃣ Customuser(⬅OAuth2User) 객체 생성 후 반환
     */
    @Transactional
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("::::::::::::::: OAuthServiceImpl - loadUser() :::::::::::::::");
        log.info("OAuth 사용자 정보를 전달받아 OAuth2User 객체로 변환합니다.");


        // 1️⃣ 주요 정보 추출
        // DefaultOAuth2UserService의 인스턴스를 생성합니다.
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();

        // - userRequest에 따라 OAuth2User 정보를 로드합니다.
        // - UserInfo 엔드포인트로부터 사용자 속성 정보를 가져옵니다. 
        //   그런 다음, 이 정보를 기반으로 OAuth2User 객체를 생성하여 반환합니다.
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();
        

        // 🧊 registrationId : 공급자 식별 키 (kakao, naver, google)
        // 클라이언트 등록 ID를 가져옵니다.
        // userRequest.등록정보가져오기().등록ID가져오기()
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // 🧊 userNameAttributeName : 공급자가 관리하는 사용자 식별 속성명
        // 사용자 정보 엔드포인트에서 사용자 이름 속성의 이름을 가져옵니다.
        // userRequest.등록정보가져오기().공급자상세정보가져오기().사용자정보엔드포인트가져오기().아이디속성명가져오기()
        // ❓ 엔드포인트 
        // : 서버의 끝점(데이터 접근 및 조작 > 서비스 로직 > 제어 > 끝점)
        //   ✅ 클라이언트가 서버에 요청을 보낼 수 있는 경로(URL, URI)
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                                                  .getUserInfoEndpoint().getUserNameAttributeName();


        log.info("★★★★★ 주요 정보 ★★★★★");
        log.info("****** registrationId : " + registrationId);
        log.info("****** userNameAttributeName : " + userNameAttributeName);
        log.info("****** attributes : " + attributes);

        // 2️⃣ OAuthAttribute 객체 생성
        OAuthAttributes oAuthAttributes =  OAuthAttributes.of(registrationId, userNameAttributeName, attributes);
        //  일원화된 정보 확인
        log.info("****** oAuthAttributes : " + oAuthAttributes);
        String nameAttributeKey = oAuthAttributes.getNameAttributeKey();
        String name = oAuthAttributes.getName();
        String email = oAuthAttributes.getEmail();
        String picture = oAuthAttributes.getPicture();
        String id = oAuthAttributes.getId();
        String provider = "";
        name = name == null ? "" : name;
        email = email == null ? "" : email;

        log.info("****** nameAttributeKey : " + nameAttributeKey);
        log.info("****** name : " + name);
        log.info("****** email : " + email);
        log.info("****** picture : " + picture);
        log.info("****** id : " + id);

        if( "kakao".equals(registrationId) ) provider = "kakao";
        if( "naver".equals(registrationId) ) provider = "naver";
        if( "google".equals(registrationId) ) provider = "google";

        log.info(":::::::::::::::::::::::::::::::::::::::::::::");
        log.info(provider + "로 로그인 합니다.");
        log.info(":::::::::::::::::::::::::::::::::::::::::::::");


        // 3️⃣ 회원 가입 또는 정보 갱신
        UserSocial userSocial = new UserSocial();
        userSocial.setProvider(provider);
        userSocial.setSocialId(id);

        Users joinedUser = null;
        try {
            joinedUser = userMapper.selectBySocial(userSocial);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // ✨👩‍💼 신규 회원
        if( joinedUser == null ) {
            log.info("***** 소셜 회원 가입 *****");
            try {
                join(userSocial, oAuthAttributes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
        // ✅👩‍💼 기존 회원
        // - 기존 회원이면, 소셜 회원 정보 변경 여부 확인 후 소셜 회원 정보 수정
        // 1️⃣ user_social 조회       [selectSocial]
        // 2️⃣ 정보 변경 여부 확인    
        // 3️⃣ user_social 수정       [updateSocial]
        else {
            log.info("***** 소설 회원 정보 갱신 *****");
            log.info("joinedUser : " + joinedUser);

             // 1️⃣ user_social 조회       [selectSocial]
            UserSocial joinedUserSocial = null;
            try {
                joinedUserSocial = userMapper.selectSocial(userSocial);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if( joinedUserSocial != null ) {
                try {
                    // 2️⃣ 정보 변경 여부 확인    
                    // 3️⃣ user_social 수정       [updateSocial]
                    update(joinedUserSocial, oAuthAttributes);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }


        // 👩‍💻 회원
        Users user = new Users();
        try {
            user = userMapper.selectBySocial(userSocial);
            log.info("***** 가입된 소셜 사용자 정보 *****");
            log.info(user.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new CustomSocialUser(user, oAuthAttributes);
    }

    /**
     * 1. 가입 여부 확인
     *      - user (provider, user_social) 조회 [userMapper.selectBySocial]
     *      - ➡ Users (joinedUser)
     * 2. joinedUserSocial 이 null 이면, 회원 가입
     *      1️⃣ user 정보 등록
     *      2️⃣ user_auth 권한 등록
     *      3️⃣ user_social 등록
     */
    @Override
    public int join(UserSocial userSocial, OAuthAttributes oAuthAttributes) throws Exception {
        // 1. 가입 여부 확인
        Users joinedUser = userMapper.selectBySocial(userSocial);

        // 2. joinedUser 이 null 이면, 회원 가입
        int result = 0;
        String username = userSocial.getProvider() + "_" + userSocial.getSocialId();
        if( joinedUser == null ) {
            Users user = new Users();
            user.setUserId(username);
            user.setUserName(oAuthAttributes.getName());
            String email = oAuthAttributes.getEmail() == null ? "" : oAuthAttributes.getEmail();
            user.setUserEmail(email);
            // user.setProfile(oAuthAttributes.getPicture());
            user.setUserPhone("");
            user.setUserBirth(new Date());
            user.setUserAddress("");
            user.setUserGender("");
            user.setUserPassword(UUID.randomUUID().toString());
            // 1️⃣ user 정보 등록
            result = userMapper.join(user);
            joinedUser = userMapper.select(username);
            int userNo = joinedUser.getUserNo();
            // 2️⃣ user_auth 권한 등록
            UserAuth userAuthUser = new UserAuth();
            userAuthUser.setUserNo(userNo);
            userAuthUser.setAuth("ROLE_USER");
            userMapper.insertAuth(userAuthUser);
        }
        if( result > 0 ) {
            // 3️⃣ user_social 등록
            UserSocial newUserSocial = new UserSocial();
            newUserSocial.setProvider(userSocial.getProvider());
            newUserSocial.setSocialId(userSocial.getSocialId());
            newUserSocial.setUsername(username);
            newUserSocial.setName(oAuthAttributes.getName());
            newUserSocial.setEmail(oAuthAttributes.getEmail());
            newUserSocial.setPicture(oAuthAttributes.getPicture());
            result += userMapper.insertSocial(newUserSocial);
        }
        return result;
    }

    /**
     * 소셜 회원 정보 수정
     */
    @Override
    public int update(UserSocial userSocial, OAuthAttributes oAuthAttributes) throws Exception {
        int result = 0;

        String name = userSocial.getName();
        String email = userSocial.getEmail();
        String picture = userSocial.getPicture();

        if( !name.equals(oAuthAttributes.getName()) )   name = oAuthAttributes.getName();
        if( !email.equals(oAuthAttributes.getEmail()) )   email = oAuthAttributes.getEmail();
        if( !picture.equals(oAuthAttributes.getPicture()) )   picture = oAuthAttributes.getPicture();

        userSocial.setName(name);
        userSocial.setEmail(email);
        userSocial.setPicture(picture);

        result = userMapper.updateSocial(userSocial);

        return result;
    }
    
}