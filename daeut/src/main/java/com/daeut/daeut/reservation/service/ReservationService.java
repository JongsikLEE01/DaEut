package com.daeut.daeut.reservation.service;

import java.util.List;

import com.daeut.daeut.main.dto.Files;
import com.daeut.daeut.main.dto.Option;
import com.daeut.daeut.main.dto.ServicePage;
import com.daeut.daeut.reservation.dto.Event;
import com.daeut.daeut.reservation.dto.Services;

public interface ReservationService {
    // 목록
    public List<Services> serviceList(ServicePage servicePage, Option option) throws Exception;
    // 단일 조회
    public Services serviceSelect(int serviceNo) throws Exception;
    // 단일 조회 수정 X
    public Services select(int serviceNo) throws Exception;
    // 삽입
    public int serviceInsert(Services service) throws Exception;
    // 업데이트
    public int serviceUpdate(Services service) throws Exception;
    // 삭제
    public int serviceDelete(int serviceNo) throws Exception;
    // 검색
    public List<Services> serviceSearch(Option option) throws Exception;
    // 썸네일
    public Files SelectThumbnail(int serviceNo) throws Exception;
    // 파트너 파일
    public Files partnerThumbnail(int partnerNo) throws Exception;
    // 설명 파일
    public List<Files> SelectFiles(int serviceNo) throws Exception;
    // 달력 리스트
    List<Event> calendarListByServiceNo(int serviceNo) throws Exception;
}
