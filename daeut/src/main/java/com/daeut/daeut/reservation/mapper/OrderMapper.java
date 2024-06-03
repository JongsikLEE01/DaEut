package com.daeut.daeut.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.daeut.daeut.reservation.dto.Orders;

@Mapper
public interface OrderMapper {
    public List<Orders> list() throws Exception;
    
    public Orders select(String ordersNo) throws Exception;
    
    public int insert(Orders orderes) throws Exception;
    
    public int update(Orders orderes) throws Exception;
    
    public int delete(String ordersNo) throws Exception;

    // ---------------------------------------------------------
    public List<Orders> listByUserNo(int userNo) throws Exception;

    public List<Orders> listByParterNo(int partnerNo) throws Exception;
}
