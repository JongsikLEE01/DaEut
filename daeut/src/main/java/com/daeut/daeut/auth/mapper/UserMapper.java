package com.daeut.daeut.auth.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.daeut.daeut.auth.dto.UserAuth;
import com.daeut.daeut.auth.dto.Users;

import com.daeut.daeut.partner.dto.Partner;
import com.daeut.daeut.reservation.dto.Orders;
import java.util.List;

@Mapper
public interface UserMapper {

    // 로그인
    public Users login(String username) throws Exception;

    // 아이디 찾기
    public  String findUserByDetails(@Param("userName") String userName, 
                                     @Param("userEmail") String userEmail, 
                                     @Param("userPhone") String userPhone) throws Exception;
                                     
    // 회원 조회
    public Users select(String username) throws Exception;

    // 이메일로 사용자 조회
    public Users findUserByEmail(String userEmail) throws Exception;

    // 회원 가입
    public int join(Users user) throws Exception;
    
    // 회원 권한 등록
    public int insertAuth(UserAuth userAuth) throws Exception;
    
    // 파트너 신청
    public int insertPartner(Partner partner);
    
    // 파트너 신청 대기
    public int updateUserStatus(@Param("userNo") int userNo);
    
    // 파트너 승인
    // TODO
    
    // 파트너 권한 추가
    // TODO

    // ----------------------------------------------------------------------------
    
    // user 및 partner 테이블에서 정보를 조회
    public Partner selectUserAndPartnerDetails(@Param("userNo") int userNo);
    
    // 회원 수정
    public int update(Users user) throws Exception;

    // 회원 탈퇴
    public int delete(Users user) throws Exception;

    // 예약 가져오기
    public List<Orders> selectOrdersByUserId(String userId) throws Exception;
    
    // 파트너 찾기
    public Partner selectPartner(int userNo) throws Exception;
    
    // 번호 유저찾기
    public Users selectByUserNo(int userNo) throws Exception;
    
    // 유저 정보 찾기
    public Users findByUsername(String username);

    
}
