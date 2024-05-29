package com.daeut.daeut.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.daeut.daeut.auth.dto.UserAuth;
import com.daeut.daeut.user.dto.User;

@Mapper
public interface UserMapper {
    public User login(String userId);
    public int join(User user) throws Exception;
    public int insertAuth(UserAuth userAuth) throws Exception;
}
