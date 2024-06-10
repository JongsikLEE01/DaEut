package com.daeut.daeut.auth.dto;

import java.sql.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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

    private Users user;
    private String userName;

    // 파일 정보
    List<MultipartFile> file;
    // 썸네일 이미지 파일
    MultipartFile thumbnail;
    // 파일 번호
    private int fileNo;

}
