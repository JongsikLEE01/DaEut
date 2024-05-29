package com.daeut.daeut.reservation.dto;

import java.sql.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Service {
    private int serviceNo;
    private String serviceCategory;
    private String serviceName;
    private int servicePrice;
    private String serviceContent;
    private Date regDate;
    private Date updDate;
    private int partnerNo;

    // 파일 정보
    List<MultipartFile> file;

    // 썸네일 이미지 파일
    MultipartFile thumbnail;
    
    // 파일 번호
    private int fileNo;
}