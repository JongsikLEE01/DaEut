package com.daeut.daeut.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.daeut.daeut.main.dto.Files;
import com.daeut.daeut.main.dto.Option;
import com.daeut.daeut.main.dto.Page;
import com.daeut.daeut.reservation.dto.Services;

@Mapper
public interface ReservationMapper {
    // 목록
    public List<Services> serviceList(@Param("page") Page page, @Param("option") Option option) throws Exception;
    // 단일 조회
    public Services serviceSelect(int serviceNo) throws Exception;
    // 삽입
    public int serviceInsert(Services service) throws Exception;
    // 업데이트
    public int serviceUpdate(Services service) throws Exception;
    // 삭제
    public int serviceDelete(int serviceNo) throws Exception;
    // 검색
    public List<Services> search(@Param("option") Option option) throws Exception;
    // 최댓값을 조회
    public int maxPk() throws Exception;
    // 개수 조회
    public int count(@Param("option") Option option) throws Exception;

    // 썸네일
    public Files SelectThumbnail(int serviceNo) throws Exception;
    // 설명 파일
    public List<Files> SelectFiles(int serviceNo) throws Exception;

}