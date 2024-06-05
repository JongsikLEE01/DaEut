package com.daeut.daeut.reservation.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ChatRooms {
    private String roomNo;
    private Boolean roomOut;
    private Date regDate;
    private int userNo;
    private int partnerNo;
    private String title;

    // ------------------------
    private List<Chats> chats;
}
