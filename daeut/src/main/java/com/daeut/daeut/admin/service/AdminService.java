package com.daeut.daeut.admin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.main.dto.Page;
import com.daeut.daeut.reservation.dto.Orders;

public interface AdminService {

    // 관리자 회원가입
    public void adminJoin(Users user, String systemPw) throws Exception;

    public int countReservations() throws Exception;
    
    public List<Orders> list(@Param("page") Page page) throws Exception;
}
