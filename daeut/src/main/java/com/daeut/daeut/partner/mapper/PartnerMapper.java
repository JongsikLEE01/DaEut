package com.daeut.daeut.partner.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.daeut.daeut.auth.dto.Review;
import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.partner.dto.Partner;

@Mapper
public interface PartnerMapper {
    
    // 파트너 조회
    public Partner getPartners(int userNo) throws Exception;

    // 파트너 수정
    public int partnerUpdate(Partner partner) throws Exception;

    // 사용자가 작성한 리뷰 모아보기
    public List<Review> getReviews(int partnerNo) throws Exception;

    // 파트너 번호를 기준으로 파트너 정보를 조회하는 메서드
    public Partner getPartnerByPartnerNo(int partnerNo);

    // 파트너 조회
    public Partner findByUserNo(int userNo) throws Exception;

    // 예약 조회 - 파트너 넘버로 
    public Partner selectByPartnerNo(int partnerNo) throws Exception;

    public Partner select(int partnerNo) throws Exception;

    public String selectUserNameByPartnerNo(int partnerNo);

    // 날짜 가져오기
    public List<String> getPartnerSchedule(@Param("partnerNo") String partnerNo);

    // 최댓값을 조회
    public int maxPk() throws Exception;
}