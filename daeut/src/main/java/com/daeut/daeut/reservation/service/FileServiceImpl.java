package com.daeut.daeut.reservation.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.daeut.daeut.reservation.dto.Files;
import com.daeut.daeut.reservation.mapper.FileMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    @Value("${upload.path}")   // application.properties에 설정한 업로드 경로를 가져옴
    private String path;

    @Override
    public List<Files> list() throws Exception {
        //  파일 목록 조회
        List<Files> fileList = fileMapper.list();
        return fileList;
    }

    @Override
    public Files select(int no) throws Exception {
        //  파일 조회
        Files file = fileMapper.select(no);
        return file;
    }

    @Override
    public int insert(Files file) throws Exception {
        //  파일 등록
        int result = fileMapper.insert(file);
        return result;
    }

    @Override
    public int update(Files file) throws Exception {
        //  파일 수정
        int result = fileMapper.update(file);
        return result;
    }

    @Override
    public int delete(int no) throws Exception {
        // 파일 정보 조회
        Files file = fileMapper.select(no);

        //  DB에서 파일 삭제
        int result = fileMapper.delete(no);

        // 실제 파일 삭제
        if(result > 0){
            String path = file.getFilePath();
            File deleteFile = new File(path);
            
            // 파일 존재 여부 확인
            if( !deleteFile.exists())  return result;

            // 파일 삭제
            if( deleteFile.delete() ){
                log.info("파일이 정상적으로 삭제...");
                log.info("file? "+path);
            }else  log.info("파일 삭제 실패...");
        }

        return result;
    }

    @Override
    public List<Files> listByParent(Files file) throws Exception {
        //  부모 테이블에 종속된 파일 목록 조회
        List<Files> fileList = fileMapper.listByParent(file);
        return fileList;
    }

    @Override
    public int deleteByParent(Files file) throws Exception {
        // 파일 목록 조회
        List<Files> fileList =  fileMapper.listByParent(file);
        
        for (Files deleteFile : fileList) {
            int no = deleteFile.getNo();
            delete(no); 
        }

        //  부모 테이블에 종속된 파일 목록 삭제
        int result = fileMapper.deleteByParent(file);

        log.info(result+"개의 파일 삭제...");
        return result;
    }

    @Override
    public boolean upload(Files file) throws Exception {
        //  파일 업로드
        log.info("file? "+ file);

        MultipartFile mf = file.getFile();
         // 파일 정보 : 원본파일명, 파일 용량, 파일 데이터
        String originFileName = mf.getOriginalFilename();
        long size = mf.getSize();
        byte[] fileData = mf.getBytes();

        log.info("원본파일명? "+originFileName);
        log.info("파일크기? "+size);
        log.info("파일데이터? "+fileData);

        // 파일 업로드
        // - 파일 시스템에 해당 파일을 복사
        // - 파일 정보를 DB에 등록

        // 업로드 경로 가져오기 -> application.properties( upload.path )
        // 파일명 가져오기 
        // - 파일명 중복방지를 위해 UID_파일명.xxx 형식으로 지정
        // 업로드 파일명 -> UID_원본파일명.xxx
        String fileName = UUID.randomUUID().toString()+ "_" +originFileName;
        File uploadFile = new File(path, fileName);

        // 파일 업로드
        FileCopyUtils.copy(fileData,uploadFile);

        String filePath = path + "/" +fileName;
        file.setFileName(fileName);
        file.setOriginFileName(originFileName);
        // filePath -> C:/upload/UID_원본파일명.xxx
        file.setFilePath(filePath);
        file.setFileSize(size);
        // file.setFileCode(0);

        fileMapper.insert(file);

        return true;
    }

    @Override
    public Files download(int no) throws Exception {
        //  파일 다운로드
        Files file = fileMapper.select(no);

        // 다운로드 시, 추가 작업
        // ...

        return file;
    }    
}