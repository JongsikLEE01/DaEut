package com.daeut.daeut.partner.service;

import java.util.List;

import com.daeut.daeut.partner.dto.Partner;

public interface PartnerService {
    
    // 파트너 조회
    public Partner getPartners(int userNo) throws Exception;
    
    // 파트너 수정
    public int partnerUpdate(Partner partner) throws Exception;
}
