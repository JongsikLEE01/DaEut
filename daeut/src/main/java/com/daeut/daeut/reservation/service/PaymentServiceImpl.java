package com.daeut.daeut.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daeut.daeut.reservation.dto.Orders;
import com.daeut.daeut.reservation.dto.Payments;
import com.daeut.daeut.reservation.mapper.PaymentMapper;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public List<Payments> list() throws Exception{
        return paymentMapper.list();
    }

    @Override
    public Payments select(int paymentNo) throws Exception{
        return paymentMapper.select(paymentNo);
    }

    @Override
    public int insert(Payments payments) throws Exception{
        Payments oldPayments = selectByOrdersNo(payments.getOrdersNo());
        if( oldPayments == null )  return paymentMapper.insert(payments);

        return 0;
    }

    @Override
    public int update(Payments payments) throws Exception{
       return paymentMapper.update(payments);
    }

    @Override
    public int delete(int paymentNo) throws Exception{
        return paymentMapper.delete(paymentNo);
    }

    @Override
    public Payments selectByOrdersNo(String ordersNo) throws Exception{
        return paymentMapper.selectByOrdersNo(ordersNo);
    }

    // 결제 내역이 있으면 update, 없으면 insert
    @Override
    public int merge(Payments payments) throws Exception {
        if( payments == null || select(payments.getPaymentNo()) == null ) 
            return insert(payments);

        return update(payments);
    }

    @Override
    public int updateData(Payments payments) throws Exception {
        return paymentMapper.updateData(payments);
    }
    
}
