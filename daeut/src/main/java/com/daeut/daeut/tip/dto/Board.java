package com.daeut.daeut.tip.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Board {
    private int boardNo;
    private String boardTitle;
    private String boardContent;
    private Date boardRegDate;
    private Date boardUpdDate;
    private int boardViews;
    private int boardLike;
    private int userNo;
}
