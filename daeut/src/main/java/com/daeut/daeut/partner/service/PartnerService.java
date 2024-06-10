package com.daeut.daeut.partner.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.daeut.daeut.auth.dto.Review;
import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.partner.dto.Partner;

@Service
public interface PartnerService {
   // 파트너 조회
   public Partner getPartners(int userNo) throws Exception;

   // 파트너 수정
   public int partnerUpdate(Partner partner, Users user) throws Exception;

   // 사용자가 작성한 리뷰 모아보기
   public List<Review> getReviews(int partnerNo) throws Exception;

   // 파트너 조회
   public Partner findByUserNo(int userNo) throws Exception;

   // 파트너 번호로 파트너 조회
   public Partner selectByPartnerNo(int partnerNo) throws Exception;

   public Partner select(int partnerNo) throws Exception;

   public Users getPartnerName(int userNo) throws Exception;

   public String selectUserNameByPartnerNo(@Param("partnerNo") int partnerNo);

   // 날짜 가져오기
   public List<String> getPartnerSchedule(String partnerNo);
}
