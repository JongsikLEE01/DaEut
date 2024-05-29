package com.daeut.daeut.partner.service;

import com.daeut.daeut.partner.dto.Partner;

public interface PartnerService {
    
    // 파트너 조회
    public Partner select(int partnerNo) throws Exception;
    
    // 파트너 수정
    public int update(Partner partner) throws Exception;
}
