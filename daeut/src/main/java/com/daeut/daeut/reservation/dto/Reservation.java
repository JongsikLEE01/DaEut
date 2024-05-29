package com.daeut.daeut.reservation.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Reservation {

    private int userNo;
    private int serviceNo;
    private int partnerNo;
    private String reservationStatus;
    private String orderNo;
    private int totalQuantity;
    private int totalPrice;
    private LocalDateTime updDate;
    private LocalDateTime regDate;
    private int cancelNo;
}
