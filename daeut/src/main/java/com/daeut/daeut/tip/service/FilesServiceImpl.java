package com.daeut.daeut.tip.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.daeut.daeut.tip.dto.Files;
import com.daeut.daeut.tip.mapper.FilesMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FilesServiceImpl implements FilesService {

    @Autowired
    private FilesMapper filesMapper;

    @Value("${upload.path}")
    private String path;

    @Override
    public List<Files> list() throws Exception {
        List<Files> fileList = filesMapper.list();
        return fileList;
    }

    @Override
    public Files select(int fileNo) throws Exception {
        Files file = filesMapper.select(fileNo);
        return file;
    }

    @Override
    public int insert(Files file) throws Exception {
        int result = filesMapper.insert(file);
        return result;
    }

    @Override
    public int update(Files file) throws Exception {
        int result = filesMapper.update(file);
        return result;
    }

    @Override
    public int delete(int fileNo) throws Exception {
        Files file = filesMapper.select(fileNo);
        int result = filesMapper.delete(fileNo);

        if (result > 0) {
            String path = file.getFilePath();
            File deleteFile = new File(path);

            if (!deleteFile.exists()) {
                return result;
            }

            // 파일 삭제
            if (deleteFile.delete()) {
                log.info("파일이 삭제되었습니다.");
                log.info("file : " + path);
            } else {
                log.info("파일 삭제에 실패하였습니다.");
            }
        }
        return result;
    }

    @Override
    public List<Files> listByParent(Files files) throws Exception {
        List<Files> fileList = filesMapper.listByParent(files);
        return fileList;
    }

    @Override
    public int deleteByParent(Files file) throws Exception {
        List<Files> fileList = filesMapper.listByParent(file);

        for (Files deleteFile : fileList) {
            int fileNo = deleteFile.getFileNo();
            delete(fileNo);
        }

        int result = filesMapper.deleteByParent(file);
        log.info(result + "개의 파일을 삭제하였습니다.");
        return result;
    }

    @Override
    public boolean upload(Files files) throws Exception {
        log.info("file : " + files);

        MultipartFile mf = files.getFile();

        String originFileName = mf.getOriginalFilename();
        long fileSize = mf.getSize();
        byte[] fileData = mf.getBytes();

        String fileName = UUID.randomUUID().toString() + "_" + originFileName;
        File uploadFile = new File(path, fileName);

        FileCopyUtils.copy(fileData, uploadFile);

        files.setFileName(fileName);
        files.setOriginFileName(originFileName);

        String filePath = path + "/" + fileName;
        files.setFilePath(filePath);
        files.setFileSize(fileSize);

        // files.setFileCode(0);
        filesMapper.insert(files);

        return true;
    }

    @Override
    public Files download(int fileNo) throws Exception {
        return filesMapper.select(fileNo);
    }
}
