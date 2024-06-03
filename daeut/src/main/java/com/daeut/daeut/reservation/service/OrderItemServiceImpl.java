package com.daeut.daeut.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daeut.daeut.reservation.dto.OrderItems;
import com.daeut.daeut.reservation.dto.Services;
import com.daeut.daeut.reservation.mapper.OrderItemMapper;
import com.daeut.daeut.reservation.mapper.ReservationMapper;

@Service
public class OrderItemServiceImpl implements OrderItemService{

    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private ReservationMapper reservationMapper;

    @Override
    public List<OrderItems> list() throws Exception {
        return orderItemMapper.list();
    }

    @Override
    public OrderItems select(String itemNo) throws Exception {
        return orderItemMapper.select(itemNo);
    }

    @Override
    public int insert(OrderItems orderItems) throws Exception {
        int result = orderItemMapper.insert(orderItems);
        return result;
    }

    @Override
    public int update(OrderItems orderItems) throws Exception {
        int result = orderItemMapper.update(orderItems);
        return result;
    }

    @Override
    public int delete(String itemNo) throws Exception {
        int result = orderItemMapper.delete(itemNo);
        return result;
    }

    @Override
    public List<OrderItems> listByOrderNo(String ordersNo) throws Exception {
        List<OrderItems> orderItems = orderItemMapper.listByOrderNo(ordersNo);

        // 주문 항목 - 상품 정보 추가
        for (OrderItems orderItem : orderItems) {
            int serviceNo = orderItem.getServiceNo();
            Services service = reservationMapper.serviceSelect(serviceNo);
            orderItem.setService(service);
        }

        return orderItems;
    }

}
