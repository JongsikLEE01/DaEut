package com.daeut.daeut.partner.dto;

import lombok.Data;

@Data
public class Partner {

    // 파트너 번호
    private int partnerNo;
    // 파트너 별점 
    private int partnerGrade;
    // 파트너 예약 횟수
    private int partnerReserve;    
    // 파트너 경력
    private int partnerCareer;     
    // 파트너 승인 상태
    private String status;         
    // 파트너 소개글
    private String introduce;
    // 사용자 번호      
    private int userNo;           

    
}
