package com.daeut.daeut.reservation.service;

import java.util.List;

import com.daeut.daeut.reservation.dto.Option;
import com.daeut.daeut.reservation.dto.Page;
import com.daeut.daeut.reservation.dto.Services;

public interface ReservationService {
    public List<Services> serviceList(Page page, Option option) throws Exception;
    public Services serviceSelect(int serviceNo) throws Exception;
    public int serviceInsert(Services services) throws Exception;
    public int serviceUpdate(Services services) throws Exception;
    public int serviceDelete(int serviceNo) throws Exception;
    // 게시글 검색
    // public List<Board> search(String keyword) throws Exception;
    public List<Services> search(Option option) throws Exception;
}
