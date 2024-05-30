package com.daeut.daeut.reservation.controller;

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

import com.daeut.daeut.reservation.dto.Files;
import com.daeut.daeut.reservation.service.FileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    FileService fileService;

    @Value("${upload.path}")   // application.properties에 설정한 업로드 경로를 가져옴
    private String path;

    /**
     * 파일 다운로드
     * @param fileNo
     * @param response
     * @throws Exception
     */
    @GetMapping("/{fileNo}")
    public void fileDownload(@PathVariable("fileNo") int fileNo
                            , HttpServletResponse response) throws Exception {
        Files downloadFile = fileService.download(fileNo);

        // 파일이 없는 경우 메소드 종료
        if(downloadFile == null) return;
        
        // 필요정보? -> 파일 경로, 파일 명
        String path = downloadFile.getFilePath(); 
        String fileName = downloadFile.getFileName();
        // 한국어 인코딩 설정
        fileName = URLEncoder.encode(fileName, "UTF-8");

        // 다운로드를 위한 응답 헤더 세팅
        // - ContentType            : application/octect-stream
        // - Content-Disposition    : attachment, name="파일명.확장자"
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        // 파일 다운로드
        File file = new File(path);
        FileInputStream fis =  new FileInputStream(file);       // 파일 입력
        ServletOutputStream sos = response.getOutputStream();   // 파일 출력
        FileCopyUtils.copy(fis,sos);

        fis.close();
        sos.close();
    }

    @DeleteMapping("/{fileNo}")
    public ResponseEntity<String> deleteFile(@PathVariable("fileNo") int fileNo) throws Exception{
        log.info("DELETE - /file/"+fileNo);

        //파일 삭제 요청
        int result = fileService.delete(fileNo);

        // 삭제 실패
        if(result == 0) return new ResponseEntity<>("FAIL",HttpStatus.OK);

        // 삭제 성공
        return new ResponseEntity<>("SUCCESS",HttpStatus.OK);
    }
    
    // /file/img/{fileNo}
    /**
     * 이미지 썸네일
     * @param param
     * @return
     */
    @GetMapping("/img/{fileNo}")
    public ResponseEntity<byte[]> tumbnailImg(@PathVariable("fileNo") int fileNo) throws Exception{
        // 파일번호로 파일 정보 조회
        Files file = fileService.select(fileNo);

        // 이미지 컨텐츠 타입 지정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        // null 체크
        if(file == null){
            String filePath = path + "/no-image.png";
            File noImgFile = new File(filePath);
            byte[] noImgFileData = FileCopyUtils.copyToByteArray(noImgFile);

            return new ResponseEntity<>(noImgFileData, headers,HttpStatus.OK);
        }

        // 파일 정보 중에서 파일 경로를 추출
        String path = file.getFilePath();

        // 파일 객체 생성
        File f = new File(path);

        // 파일 데이터
        byte[] fileData = FileCopyUtils.copyToByteArray(f);

        
        // headers.setContentType(MediaType.IMAGE_PNG);

        // new ResponseEntity<>(데이터, 헤더, 상태코드)
        return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
    }
    
}