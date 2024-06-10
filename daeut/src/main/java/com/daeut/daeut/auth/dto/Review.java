package com.daeut.daeut.auth.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class Review {
    
    private int reviewNo;
    private String reviewTitle;
    private String reviewContent;
    private Date reviewRegDate;
    private int reviewRating;
    private int userNo;
    private int paymentNo;
    private int partnerNo;
    private int serviceNo;

    private String ordersNo;
    private String userName;
}
