package com.daeut.daeut.tip.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Reply {
    private int replyNo;
    private int parentNo;
    private String replyContent;
    private Date replyRegDate;
    private Data replyUpdDate;
    private int boardNo;
    private int userNo;
}
