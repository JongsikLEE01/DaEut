package com.daeut.daeut.tip.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
    private int replyNo;
    private int parentNo;
    private String replyContent;
    private Date replyRegDate;
    private Date replyUpdDate;
    private int boardNo;
    private int userNo;
    private String userId;
}
