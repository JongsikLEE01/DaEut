package com.daeut.daeut.partner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daeut.daeut.partner.dto.Partner;
import com.daeut.daeut.partner.mapper.PartnerMapper;

@Service
public class PartnerServiceImpl implements PartnerService {

    @Autowired
    private PartnerMapper partnerMapper;

    @Override
    public Partner getPartners(int partnerNo) throws Exception {
        Partner partner = partnerMapper.getPartners(partnerNo);
        return partner;
    }

    @Override
    public int partnerUpdate(Partner partner) throws Exception {
        return partnerMapper.partnerUpdate(partner);
    }

    
    
}
