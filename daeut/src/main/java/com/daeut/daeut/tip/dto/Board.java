package com.daeut.daeut.tip.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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

    MultipartFile thumbnail;

    List<MultipartFile> file;

    private int fileNo;
}
