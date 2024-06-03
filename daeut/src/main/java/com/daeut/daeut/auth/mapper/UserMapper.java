package com.daeut.daeut.auth.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.daeut.daeut.auth.dto.UserAuth;
import com.daeut.daeut.auth.dto.Users;

import com.daeut.daeut.main.dto.Page;
import com.daeut.daeut.partner.dto.Partner;
import com.daeut.daeut.reservation.dto.Reservation;

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

    // 회원 수정
    public int update(Users user) throws Exception;

    // 회원 권한 등록
    public int insertAuth(UserAuth userAuth) throws Exception;

    // 회원 탈퇴
    public int delete(Users user) throws Exception;

    // 파트너 신청
    public void requestPartner(Users users, Partner partner) throws Exception;

    public void insertPartner(@Param("userNo") int userNo, @Param("partner") Partner partner);

    public void updateUserStatus(@Param("userNo") int userNo);

    public Users getUserById(@Param("userId") String userId);

    // 파트너 신청 승인 및 권한 추가
    public void approvePartnerAndAddAuth(@Param("userId") String userId) throws Exception;

    public List<Reservation> selectReservationsByUserId(String userId);

    // 관리자 회원가입
    public  int adminJoin(Users user) throws Exception;


    // 모든 사용자 목록 조회
    public int countUsers() throws Exception;
    public List<Users> selectAllUsers(@Param("page") Page page) throws Exception;

    // 파트너 찾기
    public Partner selectPartner(int userNo) throws Exception;

    // 관리자 - 회원 선택 
    public int deleteList(String deleteNoList) throws Exception;

    // 번호 유저찾기
    public Users selectByUserNo(int userNo) throws Exception;
    // 유저 정보 찾기
    public Users findByUsername(String username);


}
