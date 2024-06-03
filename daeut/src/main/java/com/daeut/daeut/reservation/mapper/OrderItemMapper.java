package com.daeut.daeut.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.daeut.daeut.reservation.dto.OrderItems;

@Mapper
public interface OrderItemMapper {
    public List<OrderItems> list() throws Exception;

    public OrderItems select(String itemNo) throws Exception;
    
    public int insert(OrderItems orderItems) throws Exception;
    
    public int update(OrderItems orderItems) throws Exception;
    
    public int delete(String itemNo) throws Exception;
    
    //------------------------------------------------------------------
    public List<OrderItems> listByOrderNo(String ordersNo) throws Exception;
}