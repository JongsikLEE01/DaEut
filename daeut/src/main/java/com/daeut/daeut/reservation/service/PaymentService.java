package com.daeut.daeut.reservation.service;

import java.util.List;

import com.daeut.daeut.reservation.dto.Payments;

public interface PaymentService {
    public List<Payments> list() throws Exception;

    public Payments select(int paymentNo) throws Exception;
    
    public int insert(Payments payments) throws Exception;
    
    public int update(Payments payments) throws Exception;

    public int updateData(Payments payments) throws Exception;
    
    public int delete(int paymentNo) throws Exception;

    // --------------------------------------
    public Payments selectByOrdersNo(String ordersNo) throws Exception;

    // merge : 없으면 insert, 있으면 update
    public int merge(Payments payments) throws Exception;
}
