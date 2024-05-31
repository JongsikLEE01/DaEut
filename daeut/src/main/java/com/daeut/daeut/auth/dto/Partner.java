package com.daeut.daeut.auth.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Partner {
    private int partnerGrade;
    private int partnerReserve;
    private String partnerCareer;
    private String introduce;
    private int userNo;
    private MultipartFile file;
    private String filePath;
}
