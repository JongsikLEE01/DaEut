package com.daeut.daeut.reservation.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Cancel {
    private int cancelNo;           // 취소 번호
    private String reason;          // 취소 사유
    private int cancelAomunt;       // 환불 금액
    private int confirmed;          // 취소 승인 여부 0-> 승인X 1-> 승인
    private int refund;             // 환불 승인 여부 0-> 승인X 1-> 승인
    private String cancelAccount;   // 환불 은행
    private String cancelNumber;    // 환불 계좌
    private String cancelName;      // 예금주
    private Date cancelDate;        // 취소 일자
    private Date completedDate;     // 처리 일자
    private Date regDate;           // 등록 일자
    private Date updDate;           // 수정 일자
    private String ordersNo;        // 예약 번호
}
