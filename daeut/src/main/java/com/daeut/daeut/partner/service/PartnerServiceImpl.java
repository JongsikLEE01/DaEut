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
    public List<Partner> getPartners(int userNo) {
        return partnerMapper.getPartners(userNo);
    }

    @Override
    public int update(Partner partner) throws Exception {
        int result = partnerMapper.update(partner);
        return result;
    }

    
    
}
