package com.daeut.daeut.reservation.dto;

public enum OrderStatus {
    // 보류중, 결제 완료, 확정 완료, 환불(결제 취소)
    PENDING, PAID, CONFIRMED, CANCELLED
}