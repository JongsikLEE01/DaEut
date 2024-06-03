package com.daeut.daeut.partner.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Partner {

    // 파트너 정보
    private int partnerNo;
    private int partnerGrade;
    private int partnerReserve;
    private Timestamp partnerCareer;
    private String partnerStatus;
    private String introduce;
    private int userNo;


    // 여기서부터는 user 정보
    private String userName; // 사용자 이름
    private String userPhone; // 사용자 전화번호
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userBirth; // 사용자 생년월일
    private String userAddress; // 사용자 주소
    private String userEmail; // 사용자 이메일
    private String userGender; // 사용자 성별
    private String userId; // 사용자 아이디
    private String userPassword; // 사용자 비밀번호
    private Date userRegDate; // 사용자 등록일자
    private String userCoupon; // 사용자 쿠폰
    private Date userUpdDate; // 사용자 수정일자
    private int enabled;        // 활성화 여부
    
}
