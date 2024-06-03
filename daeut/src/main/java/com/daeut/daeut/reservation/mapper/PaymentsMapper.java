package com.daeut.daeut.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.daeut.daeut.reservation.dto.Payments;

@Mapper
public interface PaymentsMapper {

    public List<Payments> list();

    public Payments select(int paymentNo);
    
    public int insert(Payments payments);
    
    public int update(Payments payments);
    
    public int delete(int paymentNo);

    // --------------------------------------
    public Payments selectByOrdersNo(String ordersNo);
}
