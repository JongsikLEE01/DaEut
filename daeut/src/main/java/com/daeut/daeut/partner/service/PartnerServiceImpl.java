package com.daeut.daeut.partner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daeut.daeut.partner.dto.Partner;
import com.daeut.daeut.partner.mapper.PartnerMapper;

@Service
public class PartnerServiceImpl implements PartnerService {

    @Autowired
    private PartnerMapper partnerMapper;

    @Override
    public Partner select(int partnerNo) throws Exception {
        Partner partner = partnerMapper.select(partnerNo);
		return partner;
       
    }

    @Override
    public int update(Partner partner) throws Exception {
        int result = partnerMapper.update(partner);
        return result;
    }

    
    
}
