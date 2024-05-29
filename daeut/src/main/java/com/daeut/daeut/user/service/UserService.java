package com.daeut.daeut.user.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.daeut.daeut.auth.dto.UserAuth;
import com.daeut.daeut.user.dto.User;

@Service
public interface UserService {
    public void login(User user, HttpServletRequest request);
    public int join(User user) throws Exception;
    public int insertAuth(UserAuth userAuth) throws Exception;
}
