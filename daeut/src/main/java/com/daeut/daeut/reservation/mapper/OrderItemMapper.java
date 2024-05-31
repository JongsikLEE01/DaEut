package com.daeut.daeut.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.daeut.daeut.reservation.dto.OrderItems;

@Mapper
public interface OrderItemMapper {
    public List<OrderItems> list() throws Exception;
    public OrderItems select(int itemNo) throws Exception;
    public int insert(OrderItems orderItems) throws Exception;
    public int update(OrderItems orderItems) throws Exception;
    public int delete(int itemNo) throws Exception;
    public OrderItems listByOrderNo(int ordersNo) throws Exception;
}
