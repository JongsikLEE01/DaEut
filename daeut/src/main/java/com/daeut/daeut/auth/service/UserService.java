package com.daeut.daeut.auth.service;

import com.daeut.daeut.auth.dto.UserAuth;
import com.daeut.daeut.auth.dto.Users;

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

    // 파트너 신청
    public void requestPartner(String userId) throws Exception;

    // 파트너 승인
    public void approvePartner(String userId) throws Exception;
}
