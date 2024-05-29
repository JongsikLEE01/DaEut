package com.daeut.daeut.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.daeut.daeut.reservation.dto.Files;
import com.daeut.daeut.reservation.dto.Option;
import com.daeut.daeut.reservation.dto.Page;
import com.daeut.daeut.reservation.dto.Reservation;
import com.daeut.daeut.reservation.dto.Service;
import com.daeut.daeut.reservation.mapper.ReservationMapper;

@org.springframework.stereotype.Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private FileService fileService;

    @Override
    public List<Service> serviceList(Page page, Option option) throws Exception {
        // 게시글 데이터 개수 조회
        int total =  reservationMapper.count(option);
        page.setTotal(total);

        // 목록
        List<Service> serviceList = reservationMapper.serviceList(page, option);

        return serviceList;
    }

    @Override
    public Service serviceSelect(int serviceNo) throws Exception {
        // 조회
        Service service = reservationMapper.serviceSelect(serviceNo);
        return service;
    }

    @Override
    public int serviceInsert(Service service) throws Exception {
        // 쓰기
        int result = reservationMapper.serviceInsert(service);

        String parentTable = "service";
        int parentNo = reservationMapper.maxPk();

        // 썸네일 업로드
        // - 부모 테이블, 부모 번호, 멀티파트파일, 파일 코드(1)-> 썸네일
        MultipartFile thumbnailFile = service.getThumbnail();

        // 썸네일 파일 업로드한 경우만 추가
        if(thumbnailFile != null && !thumbnailFile.isEmpty()){
            Files thumbnail = new Files();
            thumbnail.setFile(thumbnailFile);
            thumbnail.setParentTable(parentTable);
            thumbnail.setParentNo(parentNo);
            thumbnail.setFileCode(1);   // 썸네일 파일 코드(1)
            fileService.upload(thumbnail);       // 썸네일 파일 업로드
        }
        
        // 파일 업로드
        List<MultipartFile> fileList = service.getFile();
        if( !fileList.isEmpty() ){
            for (MultipartFile file : fileList) {
                if (file.isEmpty()) continue;

                // 파일 정보 등록
                Files  uploadFile = new Files();
                uploadFile.setFileCode(1);
                uploadFile.setParentTable(parentTable);
                uploadFile.setParentNo(parentNo);
                uploadFile.setFile(file);

                fileService.upload(uploadFile);
            }
        }

        return result;
    }

    @Override
    public int serviceUpdate(Service service) throws Exception {
        // 수정
        int result = reservationMapper.serviceUpdate(service);
        return result;
    }

    @Override
    public int serviceDelete(int serviceNo) throws Exception {
        // 삭제
        int result = reservationMapper.serviceDelete(serviceNo);
        return result;
    }
    
    @Override
    public List<Service> search(Option option) throws Exception {
        // 검색
        List<Service> boardList = reservationMapper.search(option);

        return boardList;
    }


    // 캘린더 DB 연동 

    
}
