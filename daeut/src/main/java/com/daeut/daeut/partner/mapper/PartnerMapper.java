package com.daeut.daeut.partner.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.daeut.daeut.partner.dto.Partner;

@Mapper
public interface PartnerMapper {
    
    // 파트너 조회
    List<Partner> getPartners(int userNo);

    // 파트너 수정
    public int update(Partner partner) throws Exception;

    // 사용자가 작성한 리뷰 모아보기
    
}