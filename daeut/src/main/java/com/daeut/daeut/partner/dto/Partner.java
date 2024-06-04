package com.daeut.daeut.partner.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.daeut.daeut.auth.dto.UserAuth;

import lombok.Data;

@Data
public class Partner {

    // 파트너 정보
    private int partnerNo;
    private int partnerGrade;
    private int partnerReserve;
    private Date partnerCareer;
    private String introduce;
    private int userNo;


    private MultipartFile file;
    private String filePath;

    // 권한 목록
    private List<UserAuth> authList;


}
