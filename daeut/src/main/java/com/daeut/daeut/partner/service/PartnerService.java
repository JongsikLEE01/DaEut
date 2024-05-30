package com.daeut.daeut.partner.service;

import java.util.List;

import com.daeut.daeut.partner.dto.Partner;

public interface PartnerService {
    
    // 파트너 조회
    List<Partner> getPartners(int userNo);
    
    // 파트너 수정
    public int update(Partner partner) throws Exception;
}
