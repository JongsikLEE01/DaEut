package com.daeut.daeut.reservation.dto;

import java.util.Date;

import com.daeut.daeut.partner.dto.Partner;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Reservation {
    private int reservationNo;          // 예약 번호
    private int userNo;                 // 사용자 번호
    
    private String reservationStatus;   // 예약 상태
    private String orderNo;             // 주문 번호
    private int totalQuantity;          // 총 수량
    private int totalPrice;             // 총 가격
    private Date regDate;               // 예약 등록 일자
    private Date updDate;               // 예약 수정 일자
    private int cancelNo;               // 취소 번호

    private String userName; // 추가 필드
    private String userAddress; // 추가 필드
    private String partnerName; // 추가 필드
    private String serviceName; // 추가 필드

    private Partner partner; // 파트너
    private String introduce; // 파트너 자기 소개
    private String partnerCarrer; // 파트너 커리어
}
