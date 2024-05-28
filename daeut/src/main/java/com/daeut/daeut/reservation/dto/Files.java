// 파일
package com.daeut.daeut.reservation.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;

@Data
public class Files {
    private int no;
    private String parentTable;
    private int parentNo;
    private String fileName;
    private String originFileName;
    private String filePath;
    private long fileSize;
    private Date regDate;
    private Date updDate;
    private int fileCode;

    private MultipartFile file;
}