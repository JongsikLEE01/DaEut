package com.daeut.daeut.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.daeut.daeut.reservation.dto.Option;
import com.daeut.daeut.reservation.dto.Page;
import com.daeut.daeut.reservation.dto.Service;

@Mapper
public interface ReservationMapper {
    public List<Service> serviceList(@Param("page") Page page, @Param("option") Option option) throws Exception;
    public Service serviceSelect(int serviceNo) throws Exception;
    public int serviceInsert(Service service) throws Exception;
    public int serviceUpdate(Service service) throws Exception;
    public int serviceDelete(int serviceNo) throws Exception;

    // 게시글 검색
    // @Param(파라미터명) :  한개일 경우 생략 가능, 여러개 일 경우 명시 해야함
    // public List<Board> search(@Param("keyword") String keyword) throws Exception;
    public List<Service> search(@Param("option") Option option) throws Exception;

    // 게시글 번호(기본키) 최댓값
    public int maxPk() throws Exception;

    // 게시글 데이터 개수 조회
    public int count(@Param("option") Option option) throws Exception;
}