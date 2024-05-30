package com.daeut.daeut.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.daeut.daeut.reservation.dto.Files;
import com.daeut.daeut.reservation.dto.Option;
import com.daeut.daeut.reservation.dto.Page;
import com.daeut.daeut.reservation.dto.Services;
import com.daeut.daeut.reservation.mapper.ReservationMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private FileService fileService;

    private static final int THUMBNAIL_FILE_CODE = 1;

    @Override
    public List<Services> serviceList(Page page, Option option) throws Exception {
        // 게시글 데이터 개수 조회
        int total = reservationMapper.count(option);
        page.setTotal(total);
        
        // 목록 조회
        List<Services> serviceList = reservationMapper.serviceList(page, option);
        log.info("목록: {}", serviceList);

        return serviceList;
    }

    @Override
    public Services serviceSelect(int serviceNo) throws Exception {
        // 조회
        Services service = reservationMapper.serviceSelect(serviceNo);

        return service;
    }

    @Override
    public int serviceInsert(Services service) throws Exception {
        // 쓰기
        int result = reservationMapper.serviceInsert(service);

        String parentTable = "service";
        int parentNo = reservationMapper.maxPk();

        // 썸네일 업로드
        MultipartFile thumbnailFile = service.getThumbnail();
        if (thumbnailFile != null && !thumbnailFile.isEmpty()) {
            Files thumbnail = new Files();
            thumbnail.setFile(thumbnailFile);
            thumbnail.setParentTable(parentTable);
            thumbnail.setParentNo(parentNo);
            thumbnail.setFileCode(THUMBNAIL_FILE_CODE);
            fileService.upload(thumbnail);
        }
        
        // 파일 업로드
        List<MultipartFile> fileList = service.getFile();
        if (fileList != null && !fileList.isEmpty()) {
            for (MultipartFile file : fileList) {
                if (file.isEmpty()) continue;

                Files uploadFile = new Files();
                uploadFile.setParentTable(parentTable);
                uploadFile.setParentNo(parentNo);
                uploadFile.setFile(file);

                fileService.upload(uploadFile);
            }
        }

        return result;
    }

    @Override
    public int serviceUpdate(Services service) throws Exception {
        // 수정
        return reservationMapper.serviceUpdate(service);
    }

    @Override
    public int serviceDelete(int serviceNo) throws Exception {
        // 삭제
        return reservationMapper.serviceDelete(serviceNo);
    }
    
    @Override
    public List<Services> search(Option option) throws Exception {
        // 검색
        return reservationMapper.search(option);
    }

    @Override
    public Files SelectThumbnail(int serviceNo) throws Exception {
        // 썸네일
        Files thumbnail = reservationMapper.SelectThumbnail(serviceNo);
        log.info("thumbnail? {}",thumbnail);

        return thumbnail;
    }
    
    @Override
    public List<Files> SelectFiles(int serviceNo) throws Exception {
        // 파일
        List<Files> files = reservationMapper.SelectFiles(serviceNo);
        log.info("files? {}",files);
        
        return files;
    }
}
