package com.daeut.daeut.tip.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.daeut.daeut.tip.dto.Files;

public interface FilesService {
    // 파일 목록
    public List<Files> list() throws Exception;
    // 파일 조회
    public Files select(int fileNo) throws Exception;
    // 파일 등록
    public int insert(Files file) throws Exception;
    // 파일 수정
    public int update(Files file) throws Exception;
    // 파일 삭제
    public int delete(int fileNo) throws Exception;

    // 파일 목록 - 부모 기준
    public List<Files> listByParent(int parentNo) throws Exception;
    // 파일 삭제 - 부모 기준
    public int deleteByParent(int parentNo) throws Exception;
    public int upload(String parentTable, int parentNo, List<MultipartFile> fileList) throws Exception;
}
