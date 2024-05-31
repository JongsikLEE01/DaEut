package com.daeut.daeut.reservation.service;

import java.util.List;

import com.daeut.daeut.reservation.dto.OrderItems;

public interface OrderItemService {
    public List<OrderItems> list() throws Exception;
    public OrderItems select(int itemNo) throws Exception;
    public int insert(OrderItems orderItems) throws Exception;
    public int update(OrderItems orderItems) throws Exception;
    public int delete(int itemNo) throws Exception;
    public OrderItems listByOrderNo(int ordersNo) throws Exception;
}
