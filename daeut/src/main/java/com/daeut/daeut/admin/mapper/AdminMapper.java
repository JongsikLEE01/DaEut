package com.daeut.daeut.admin.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.main.dto.Page;
import com.daeut.daeut.partner.dto.Partner;
import com.daeut.daeut.reservation.dto.Orders;


@Mapper
public interface AdminMapper {

    // 관리자 회원가입
    public  int adminJoin(Users user) throws Exception;

    // 모든 사용자 목록 조회
    public int countUsers() throws Exception;
    public List<Users> selectAllUsers(@Param("page") Page page) throws Exception;

    // 모든 파트너 목록 조회
    public int countPartners() throws Exception;
    public List<Partner> selectAllPartners(@Param("page") Page page) throws Exception;

    // 관리자 - 회원 선택 삭제
    public int deleteList(String deleteNoList) throws Exception;

    // 관리자 - 회원 조회
    public Users findUserById(int userNo) throws Exception;
    
    // 관리자 - 회원 수정
    public int adminUpdateUser(Users user) throws Exception;
    
    // 관리자 - 회원 삭제
    public int adminDeleteUser(int userNo) throws Exception;  

    // 관리자 - 파트너 조회
    public Partner findPartnerById(int userNo) throws Exception;
    
    // 관리자 - 회원 수정
    public int adminUpdatePartner(Partner partner) throws Exception;
    
    // 예약된 수를 카운트하는 쿼리 
    public int countReservations() throws Exception;

    // 모든 주문 조회
    public List<Orders> list(@Param("page") Page page) throws Exception;
}