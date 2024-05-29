package com.daeut.daeut.tip.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.daeut.daeut.tip.dto.Files;
import com.daeut.daeut.tip.mapper.FilesMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FilesServiceImpl implements FilesService {

    @Autowired
    private FilesMapper filesMapper;

    private final String uploadDir = "C:/upload/";

    public List<Files> list() throws Exception {
        List<Files> filesList = filesMapper.list();
        return filesList;
    }

    @Override
    public Files select(int fileNo) throws Exception {
        Files files = filesMapper.select(fileNo);
        return files;
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
        return filesMapper.delete(fileNo);
    }

    @Override
    public List<Files> listByParent(int parentNo) throws Exception {
        return filesMapper.listByParent(parentNo);
    }

    @Override
    public int deleteByParent(int parentNo) throws Exception {
        return filesMapper.deleteByParent(parentNo);
    }

    @Override
    public int upload(String parentTable, int parentNo, List<MultipartFile> fileList) throws Exception {
        for (MultipartFile file : fileList) {
            if (file.isEmpty()) {
                continue;
            }

            String originalFileName = file.getOriginalFilename();
            String storedFileName = UUID.randomUUID().toString() + "_" + originalFileName;
            
            File destinationFile = new File(uploadDir + storedFileName);

            if (!destinationFile.getParentFile().exists()) {
                destinationFile.getParentFile().mkdirs();
            }

            try {
                file.transferTo(destinationFile);
            } catch (IOException e) {
                throw new Exception("Failed to upload file: " + originalFileName, e);
            }

            Files files = new Files();
            files.setParentTable(parentTable);
            files.setParentNo(parentNo);
            files.setFileName(storedFileName);
            files.setOriginFileName(originalFileName);
            files.setFilePath(destinationFile.getAbsolutePath());
            files.setFileSize((int) file.getSize());

            filesMapper.insert(files);
        }
        return 1;
    }

    
    
}
