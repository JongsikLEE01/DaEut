package com.daeut.daeut.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daeut.daeut.auth.dto.UserAuth;
import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.auth.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public boolean login(Users user) throws Exception {
        // // 💍 토큰 생성
        String username = user.getUserId();    // 아이디
        String password = user.getUserPassword();    // 암호화되지 않은 비밀번호
        UsernamePasswordAuthenticationToken token 
            = new UsernamePasswordAuthenticationToken(username, password);
        
        // 토큰을 이용하여 인증
        Authentication authentication = authenticationManager.authenticate(token);

        // 인증 여부 확인
        boolean result = authentication.isAuthenticated();

        // 시큐리티 컨텍스트에 등록
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return result;
    }

    // id로 조회
    @Override
    public Users select(String username) throws Exception {
        Users user = userMapper.select(username);
        return user;
    }

    // email 로 조회
    @Override
    public Users findUserByEmail(String userEmail) throws Exception {
        Users user = userMapper.findUserByEmail(userEmail);
        return user;
    }

     // 아이디 찾기
    @Override
    public String findUserByDetails(String userName, String userEmail, String userPhone) throws Exception {
        return userMapper.findUserByDetails(userName, userEmail, userPhone);
    }

    @Override
    public int join(Users user) throws Exception {
        String username = user.getUserId();
        String password = user.getUserPassword();
        String encodedPassword = passwordEncoder.encode(password);  // 🔒 비밀번호 암호화
        user.setUserPassword(encodedPassword);

        // 회원 등록
        int result = userMapper.join(user);

        if( result > 0 ) {
            Users joinedUser = userMapper.select(username);
            int userNo = joinedUser.getUserNo();
            // 회원 기본 권한 등록
            UserAuth userAuth = new UserAuth();
            userAuth.setUserNo(userNo);
            userAuth.setAuth("ROLE_USER");
            result = userMapper.insertAuth(userAuth);
        }
        return result;
    }

    @Transactional
    @Override
    public int update(Users user) throws Exception {
        int result = userMapper.update(user);
        return result;
    }

    @Override
    public int insertAuth(UserAuth userAuth) throws Exception {
        int result = userMapper.insertAuth(userAuth);
        return result;
    }

    @Transactional
    @Override
    public int delete(Users user) throws Exception {
        int result = userMapper.delete(user);
        return result;
    }

     @Override
    public void requestPartner(String userId) throws Exception {
        userMapper.requestPartner(userId);
    }

    @Override
    @Transactional
    public void approvePartner(String userId) throws Exception {
        userMapper.approvePartnerAndAddAuth(userId);
    }

}