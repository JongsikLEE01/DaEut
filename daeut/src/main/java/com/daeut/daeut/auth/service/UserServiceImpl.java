package com.daeut.daeut.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import com.daeut.daeut.auth.dto.UserAuth;
import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.auth.mapper.UserMapper;

import com.daeut.daeut.main.dto.Page;
import com.daeut.daeut.partner.dto.Partner;

import com.daeut.daeut.reservation.dto.Reservation;


import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${system.pw}")
    private String systemPw;

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
    public void requestPartner(Users user, Partner partner) throws Exception {
        String filePath = saveFile(partner.getFile());
        partner.setFilePath(filePath);
        userMapper.insertPartner(user.getUserNo(), partner);
        userMapper.updateUserStatus(user.getUserNo());
    }

    @Override
    public Users getUserById(String userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    @Transactional
    public void approvePartner(String userId) throws Exception {
        userMapper.approvePartnerAndAddAuth(userId);
    }

    @Override
    public List<Reservation> getUserReservations(String userId) {
        return userMapper.selectReservationsByUserId(userId);
    }

    @Transactional
    @Override
    public void adminJoin(Users user, String systemPw) throws Exception {
        if (!this.systemPw.equals(systemPw)) {
            throw new IllegalArgumentException("시스템 비밀번호가 잘못되었습니다.");
        }
        String password = user.getUserPassword();
        String encodedPassword = passwordEncoder.encode(password);
        user.setUserPassword(encodedPassword);
        int result = userMapper.join(user);
        if (result > 0) {
            Users joinedUser = userMapper.select(user.getUserId());
            int userNo = joinedUser.getUserNo();
            UserAuth userAuthUser = new UserAuth();
            userAuthUser.setUserNo(userNo);
            userAuthUser.setAuth("ROLE_USER");
            userMapper.insertAuth(userAuthUser);
            UserAuth userAuthPartner = new UserAuth();
            userAuthPartner.setUserNo(userNo);
            userAuthPartner.setAuth("ROLE_PARTNER");
            userMapper.insertAuth(userAuthPartner);
            UserAuth userAuthAdmin = new UserAuth();
            userAuthAdmin.setUserNo(userNo);
            userAuthAdmin.setAuth("ROLE_ADMIN");
            userMapper.insertAuth(userAuthAdmin);
        }
    }

    @Override
    public int countUsers() throws Exception {
        return userMapper.countUsers();
    }
    
  
    private String saveFile(MultipartFile file) {
        return "c:/upload";
    }

    // 모든 사용자 목록 조회
    @Override
    public List<Users> selectAllUsers(Page page) throws Exception {
        List<Users> userList = userMapper.selectAllUsers(page);
        // ROLE_USER만 필터링
        log.info("user: " + userList);
        return userList;
    }

    @Override
    public Partner selectPartner(int userNo) throws Exception {
        Partner partner = userMapper.selectPartner(userNo);
        return partner;

    }
  
    @Override
    public Users selectByUserNo(int userNo) throws Exception {
        return userMapper.selectByUserNo(userNo);
    }
    @Override
    public Users findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public int deleteList(String[] deleteNoList) throws Exception {
        String deleteNos = String.join(",", deleteNoList);
        int result = userMapper.deleteList(deleteNos);
        return result;
    }


}
