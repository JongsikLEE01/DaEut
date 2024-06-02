package com.daeut.daeut.reservation.service;

import java.util.List;

import com.daeut.daeut.reservation.dto.OrderItems;

public interface OrderItemService {
    public List<OrderItems> list() throws Exception;

    public OrderItems select(String itemNo) throws Exception;

    public int insert(OrderItems orderItems) throws Exception;

    public int update(OrderItems orderItems) throws Exception;

    public int delete(String itemNo) throws Exception;

    //------------------------------------------------------------------
    public List<OrderItems> listByOrderId(String ordersNo) throws Exception;
}
