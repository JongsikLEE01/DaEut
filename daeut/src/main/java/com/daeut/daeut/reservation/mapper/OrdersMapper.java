package com.daeut.daeut.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.daeut.daeut.reservation.dto.Orders;

@Mapper
public interface OrdersMapper {
    public List<Orders> list() throws Exception;
    public Orders select(int ordersNo) throws Exception;
    public int insert(Orders orders) throws Exception;
    public int update(Orders orders) throws Exception;
    public int delete(int ordersNo) throws Exception;
    public Orders listByUserNo(int usersNo) throws Exception;
}
