package com.daeut.daeut.partner.dto;

import java.sql.Timestamp;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Partner {

    // 파트너 정보
    private int partnerNo;
    private int partnerGrade;
    private int partnerReserve;
    private Timestamp partnerCareer;
    private String introduce;
    private int userNo;

    // private MultipartFile file;
    // private String filePath;

}
