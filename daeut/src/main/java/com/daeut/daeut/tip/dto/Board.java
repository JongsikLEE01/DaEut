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

    // 썸네일 이미지 파일
    MultipartFile thumbnail;

    // 파일
    List<MultipartFile> file;

    // 파일 번호
    private int fileNo;

    // private List<Integer> likedUsers;
}
