package com.daeut.daeut.reservation.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.daeut.daeut.reservation.dto.Orders;

@Mapper
public interface OrdersService {
    public List<Orders> list() throws Exception;

    public Orders select(String ordersNo) throws Exception;

    public int insert(Orders orders) throws Exception;

    public int update(Orders orders) throws Exception;

    public int delete(String ordersNo) throws Exception;

    // ---------------------------------------------------------
    public List<Orders> listByUserId(int userNo) throws Exception;
}
