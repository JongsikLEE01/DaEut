package com.daeut.daeut.tip.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daeut.daeut.tip.dto.Files;
import com.daeut.daeut.tip.service.FilesService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/file")
public class FilesController {
    
    @Autowired
    private FilesService filesService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/{no}")
    public void fileDownload(@PathVariable("no") int no
                             ,HttpServletResponse response ) throws Exception {
        Files downloadFile = filesService.download(no);

        if( downloadFile == null ) {
            return;
        }

        String fileName = downloadFile.getFileName();
        String filePath = downloadFile.getFilePath();

        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);

        fileName = URLEncoder.encode(fileName, "UTF-8");

        response.setHeader("Content-Disposition"
                               ,"attachment; filename=\"" + fileName + "\"");

        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        ServletOutputStream sos = response.getOutputStream();
        FileCopyUtils.copy(fis, sos);

        fis.close();
        sos.close();
    }
    
    @DeleteMapping("/{no}")
    public ResponseEntity<String> deleteFile(@PathVariable("no") int no) throws Exception {
        log.info("[DELETE] - /file/" + no);

        int result = filesService.delete(no);

        if( result > 0 ) {
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }

        return new ResponseEntity<>("FAIL", HttpStatus.OK);
    }

    @GetMapping("/img/{no}")
    public ResponseEntity<byte[]> thumbnailImg(@PathVariable("no") int no) throws Exception {

        Files file = filesService.select(no);

        if( file == null ) {
            String filePath = uploadPath + "/no-image.png";
            File noImagFile = new File(filePath);
            byte[] noImageFileData = FileCopyUtils.copyToByteArray(noImagFile);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(noImageFileData, headers, HttpStatus.OK);
        }
        
        String filePath = file.getFilePath();

        File f = new File(filePath);

        byte[] fileData = FileCopyUtils.copyToByteArray(f);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
    }
    
}
