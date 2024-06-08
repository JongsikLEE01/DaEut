package com.daeut.daeut.partner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daeut.daeut.auth.dto.Review;
import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.auth.service.UserService;
import com.daeut.daeut.partner.dto.Partner;
import com.daeut.daeut.partner.mapper.PartnerMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PartnerServiceImpl implements PartnerService {

    @Autowired
    private PartnerMapper partnerMapper;

    @Autowired
    private UserService userService;

    public PartnerServiceImpl(PartnerMapper partnerMapper) {
        this.partnerMapper = partnerMapper;
    }


    // 파트너 정보 가져오기
    @Override
    public Partner getPartners(int userNo) throws Exception {
        try {
            Partner partner = partnerMapper.getPartners(userNo);
            return partner;
        } catch (Exception e) {
            // 에러 로그 기록
            log.error("Error while fetching partners for userNo: {}", userNo, e);
            throw e;
        }
    }

    // 파트너 정보 수정
    @Override
    @Transactional
    public int partnerUpdate(Partner partner, Users user) throws Exception {

        log.info("Updating user: {}", user);
        log.info("Updating partner: {}", partner);

        int userUpdateResult = userService.update(user);
        log.info("User update result: {}", userUpdateResult);

        log.info(partner.toString()+"dsdfdfdf");

        int partnerUpdateResult = partnerMapper.partnerUpdate(partner);
        log.info("Partner update result: {}", partnerUpdateResult);
    
        // 둘 중 하나라도 실패하면 실패로 처리하기
        int result = userUpdateResult + partnerUpdateResult;
    
        return result;
    }

    // 리뷰 모아보기
    @Override
    public List<Review> getReviews(int partnerNo) throws Exception {
        return partnerMapper.getReviews(partnerNo);
    }

    @Override
    public Partner findByUserNo(int userNo) throws Exception {
        return partnerMapper.findByUserNo(userNo);
    }

    @Override
    public Partner selectByPartnerNo(int parterNo) throws Exception {
        return partnerMapper.selectByPartnerNo(parterNo);
    }

    @Override
    public Partner select(int parterNo) throws Exception {
        return partnerMapper.select(parterNo);
    }

    @Override
    public Users getPartnerName(int parterNo) throws Exception {
        Partner partner = select(parterNo);
        int userNo = partner.getUserNo();
        Users uPartner = userService.findUserById(userNo);

        return uPartner;
    }

    @Override
    public String selectUserNameByPartnerNo(int partnerNo) {
        return partnerMapper.selectUserNameByPartnerNo(partnerNo);
    }
    
    // 날짜 가져오기
    @Override
    public List<String> getPartnerSchedule(String partnerNo) {
        return partnerMapper.getPartnerSchedule(partnerNo);
    }
    
}
