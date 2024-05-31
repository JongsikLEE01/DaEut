package com.daeut.daeut.reservation.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Orders {
    private int ordersNo;
    private String reservationStatus;
    private String orderUid;
    private int totalQuantity;
    private int totalPrice;
    private Date updDate;
    private Date regDate;
    
    private int userNo;
    private int partnerNo;
}
