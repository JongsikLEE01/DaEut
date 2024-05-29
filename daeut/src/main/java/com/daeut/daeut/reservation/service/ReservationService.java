package com.daeut.daeut.reservation.service;

import java.util.List;

import com.daeut.daeut.reservation.dto.Option;
import com.daeut.daeut.reservation.dto.Page;
import com.daeut.daeut.reservation.dto.Reservation;
import com.daeut.daeut.reservation.dto.Service;

public interface ReservationService {
    public List<Service> serviceList(Page page, Option option) throws Exception;
    public Service serviceSelect(int serviceNo) throws Exception;
    public int serviceInsert(Service service) throws Exception;
    public int serviceUpdate(Service service) throws Exception;
    public int serviceDelete(int serviceNo) throws Exception;
    // 게시글 검색
    // public List<Board> search(String keyword) throws Exception;
    public List<Service> search(Option option) throws Exception;
    // 캘린더 DB 연동
}
