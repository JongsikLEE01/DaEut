package com.daeut.daeut.tip.dto;

import java.util.Date;
import lombok.Data;

@Data
public class Files {
    private int fileNo;
    private String parentTable;
    private int parentNo;
    private String fileName;
    private String originFileName;
    private String filePath;
    private int fileSize;
    private Date fileRegDate;
    private Date fileUpdDate;
    private int fileCode;
}
