package com.daeut.daeut.partner.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Review {
    
    private int reviewNo;
    private String reviewTitle;
    private String reviewContent;
    private Timestamp reviewRegDate;
    private int reviewRating;
    private int userNo;
    private int paymentNo;
    private int partnerNo;
    private int serviceNo;
}
