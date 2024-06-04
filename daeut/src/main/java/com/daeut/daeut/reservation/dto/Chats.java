package com.daeut.daeut.reservation.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Chats {
    private int chatNo;
    private String chatContent;
    private Date chatRegDate;
    private int userNo;
    private int roomNo;

    // -----------------------------
    private ChatRooms chatRooms;
}
