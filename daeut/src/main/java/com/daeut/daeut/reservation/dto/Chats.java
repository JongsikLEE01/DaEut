package com.daeut.daeut.reservation.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Chats {
    private int chatNo;
    private String chatContent;

    // @JsonFormat = 원하는 날짜 형식으로 변경
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date chatRegDate;

    private int userNo;
    private String roomNo;

    // -----------------------------
    private ChatRooms chatRooms;
}
