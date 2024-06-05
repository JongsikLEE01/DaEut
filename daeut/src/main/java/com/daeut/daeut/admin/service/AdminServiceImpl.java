package com.daeut.daeut.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daeut.daeut.admin.mapper.AdminMapper;
import com.daeut.daeut.auth.dto.UserAuth;
import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.auth.mapper.UserMapper;
import com.daeut.daeut.main.dto.Page;
import com.daeut.daeut.reservation.dto.Orders;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${system.pw}")
    private String systemPw;

    // 관리자 회원가입
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
    public int countReservations() throws Exception {
        return adminMapper.countReservations();
    }

    @Override
    public List<Orders> list(Page page) throws Exception {
        return adminMapper.list(page);
    }

}
