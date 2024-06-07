package com.daeut.daeut.reservation.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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

    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date serviceDate;
}
