package com.daeut.daeut.reservation.dto;

import java.util.Date;

import lombok.Data;

@Data
public class OrderItems {
    private int itemNo;
    private int quantity;
    private int price;
    private Integer amount;
    private Date updDate;
    private Date regDate;
    private int ordersNo;
    private int serviceNo;
}
