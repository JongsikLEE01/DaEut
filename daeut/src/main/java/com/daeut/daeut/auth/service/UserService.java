package com.daeut.daeut.auth.service;

import com.daeut.daeut.auth.dto.UserAuth;
import com.daeut.daeut.auth.dto.Users;

import com.daeut.daeut.main.dto.Page;

import com.daeut.daeut.reservation.dto.Reservation;
import com.daeut.daeut.partner.dto.Partner;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserService {

    // 로그인
    public boolean login(Users user) throws Exception;

    // 아이디 찾기
    public String findUserByDetails(String userName, String userEmail, String userPhone) throws Exception;

    // 이메일 중복 검사
    public Users findUserByEmail(String userEmail) throws Exception;

    // 조회
    public Users select(String username) throws Exception;

    // 회원 가입
    public int join(Users user) throws Exception;

    // 회원 수정
    public int update(Users user) throws Exception;

    // 회원 권한 등록
    public int insertAuth(UserAuth userAuth) throws Exception;

    // 회원 탈퇴
    public int delete(Users user) throws Exception;

    // 파트너 신청
    public int insertPartner(Partner partner) throws Exception;

    // 파트너 신청 대기
    public int updateUserStatus(int userNo) throws Exception;

    // user 및 partner 테이블에서 정보를 조회
    public Partner selectUserAndPartnerDetails(int userNo) throws Exception;

    // 파트너 승인
    // TODO

    // 파트너 권한 추가
    // TODO

    // 예약
    public List<Reservation> getUserReservations(String userId) throws Exception;

    // 관리자 회원가입
    public void adminJoin(Users user, String systemPw) throws Exception;

    // 모든 사용자 목록 조회
    public int countUsers() throws Exception;
    public List<Users> selectAllUsers(Page page) throws Exception;

    // 파트너 찾기
    public Partner selectPartner(int userNo) throws Exception;

    // 번호 유저찾기
    public Users selectByUserNo(int userNo) throws Exception;

    // 유저 이름으로 찾기
    public Users findByUsername(String username);

    public int deleteList(String[] deleteNoList) throws Exception;

    // 관리자 - 회원 조회
    public Users findUserById(int userNo) throws Exception;
    // 관리자 - 회원 수정
    public int adminUpdateUser(Users user) throws Exception;
    // 관리자 - 회원 삭제
    public int adminDeleteUser(int userNo) throws Exception;  

    // 모든 파트너 목록 조회
    public int countPartners() throws Exception;
    public List<Partner> selectAllPartners(@Param("page") Page page) throws Exception;

    // 관리자 - 파트너 조회
    public Partner findPartnerById(int userNo) throws Exception;
    // 관리자 - 회원 수정
    public int adminUpdatePartner(Partner partner) throws Exception;

}
