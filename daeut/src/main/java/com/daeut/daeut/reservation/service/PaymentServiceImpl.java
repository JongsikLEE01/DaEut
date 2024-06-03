package com.daeut.daeut.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.daeut.daeut.reservation.dto.Payments;
import com.daeut.daeut.reservation.mapper.PaymentsMapper;

public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentsMapper paymentsMapper;

    @Override
    public List<Payments> list() throws Exception{
        return paymentsMapper.list();
    }

    @Override
    public Payments select(int paymentNo) throws Exception{
        return paymentsMapper.select(paymentNo);
    }

    @Override
    public int insert(Payments payments) throws Exception{
        Payments oldPayments = selectByOrdersNo(payments.getOrdersNo());
        if( oldPayments == null )  return paymentsMapper.insert(payments);

        return 0;
    }

    @Override
    public int update(Payments payments) throws Exception{
       return paymentsMapper.update(payments);
    }

    @Override
    public int delete(int paymentNo) throws Exception{
        return paymentsMapper.delete(paymentNo);
    }

    @Override
    public Payments selectByOrdersNo(String ordersNo) throws Exception{
        return paymentsMapper.selectByOrdersNo(ordersNo);
    }

    // 결제 내역이 있으면 update, 없으면 insert
    @Override
    public int merge(Payments payments) throws Exception {
        if( payments == null || select(payments.getPaymentNo()) == null ) 
            return insert(payments);

        return update(payments);
    }
    
}
