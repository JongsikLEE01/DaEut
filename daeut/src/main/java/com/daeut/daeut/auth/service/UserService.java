package com.daeut.daeut.auth.service;

import com.daeut.daeut.auth.dto.UserAuth;
import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.reservation.dto.Orders;
import com.daeut.daeut.partner.dto.Partner;

import java.util.List;

public interface UserService {

    // 로그인
    public boolean login(Users user) throws Exception;

    // 아이디 찾기
    public String findUserByDetails(String userName, String userEmail, String userPhone) throws Exception;
    
    // 조회
    public Users select(String username) throws Exception;

    // 이메일 중복 검사
    public Users findUserByEmail(String userEmail) throws Exception;

    // 회원 가입
    public int join(Users user) throws Exception;

    // 회원 권한 등록
    public int insertAuth(UserAuth userAuth) throws Exception;

    // 파트너 신청
    public int insertPartner(Partner partner) throws Exception;

    // 파트너 신청 대기
    public int updateUserStatus(int userNo) throws Exception;

    // ----------------------------------------------------------------------------

    // user 및 partner 테이블에서 정보를 조회
    public Partner selectUserAndPartnerDetails(int userNo) throws Exception;

    // 회원 수정
    public int update(Users user) throws Exception;

    // 회원 탈퇴
    public int delete(Users user) throws Exception;

    // 예약
    public List<Orders> selectOrdersByUserId(String userId) throws Exception;

    // 파트너 찾기
    public Partner selectPartner(int userNo) throws Exception;

    // 번호 유저찾기
    public Users selectByUserNo(int userNo) throws Exception;

    // 유저 이름으로 찾기
    public Users findByUsername(String username);

    

   
}
