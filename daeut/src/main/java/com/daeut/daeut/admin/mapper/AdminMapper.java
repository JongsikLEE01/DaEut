package com.daeut.daeut.admin.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.main.dto.Page;
import com.daeut.daeut.reservation.dto.Orders;


@Mapper
public interface AdminMapper {

    // 관리자 회원가입
    public  int adminJoin(Users user) throws Exception;

    public int countReservations() throws Exception;

    public List<Orders> list(@Param("page") Page page) throws Exception;
}