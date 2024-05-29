package com.daeut.daeut.partner.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.daeut.daeut.partner.dto.Partner;

@Mapper
public interface PartnerMapper {
    
    // 파트너 조회
    public Partner select(int partnerNo) throws Exception;

    // 파트너 수정
    public int update(Partner partner) throws Exception;
    
}