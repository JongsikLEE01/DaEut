package com.daeut.daeut.partner.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.daeut.daeut.partner.dto.Partner;

@Mapper
public interface PartnerMapper {
    
    // 파트너 조회
    public Partner getPartners(int userNo) throws Exception;

    // 파트너 수정
    public int partnerUpdate(Partner partner) throws Exception;

    // 사용자가 작성한 리뷰 모아보기
    
}