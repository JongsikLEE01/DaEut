package com.daeut.daeut.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.daeut.daeut.reservation.dto.Option;
import com.daeut.daeut.reservation.dto.Page;
import com.daeut.daeut.reservation.dto.Reservation;
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
    // 예약 DB 연동
    @Select("SELECT reg_date FROM reservation")
    List<Reservation> getAllReservations();
}