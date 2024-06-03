package com.daeut.daeut.auth.dto;

import java.util.Date;
import lombok.Data;

@Data
public class Reservation {
    private int reservationNo;          // 예약 번호
    private int userNo;                 // 사용자 번호
    private int serviceNo;              // 서비스 번호
    private int partnerNo;              // 파트너 번호
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
}
