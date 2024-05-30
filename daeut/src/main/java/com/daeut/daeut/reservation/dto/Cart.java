package com.daeut.daeut.reservation.dto;

import java.util.Date;
import lombok.Data;

@Data
public class Cart {
    private int cartNo;
    private int cartAmount;
    private Date cartRegDate;
    private Date cartUpdDate;

    private int serviceNo;
    private int userNo;
}
