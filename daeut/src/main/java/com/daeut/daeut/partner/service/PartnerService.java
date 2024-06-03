package com.daeut.daeut.partner.service;

import java.util.List;

import com.daeut.daeut.partner.dto.Partner;
import com.daeut.daeut.partner.dto.Review;

public interface PartnerService {
    
    // 파트너 조회
    public Partner getPartners(int userNo) throws Exception;
    
    // 파트너 수정
    public int partnerUpdate(Partner partner) throws Exception;

    // 리뷰 모아보기
    List<Review> getReviews(int partnerNo) throws Exception;

    // 파트너 찾기
    public Partner findByUserNo(int userNo) throws Exception;
    public List<Review> getReviewsByPartnerNo(int partnerNo) throws Exception;
}