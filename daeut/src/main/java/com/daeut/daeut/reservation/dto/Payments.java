package com.daeut.daeut.reservation.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Payments {
    private int paymentNo;
    private String paymentMethod;
    private PaymentStatus status;
    private Date payDate;
    private Date regDate;
    private Date updDate;
    private String ordersNo;
    private Date serviceDate;
}
