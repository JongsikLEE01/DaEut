package com.daeut.daeut.reservation.dto;

import java.util.Date;

import lombok.Data;

@Data
public class OrderItems {
    private String itemNo;
    private String ordersNo; 
    private int serviceNo;
    private int quantity = 1;
    private int price = 0;
    private Integer amount;
    private Date regDate;
    private Date updDate;

    private Services service;
}
