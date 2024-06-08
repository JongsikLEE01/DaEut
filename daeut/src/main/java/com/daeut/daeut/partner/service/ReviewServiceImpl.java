package com.daeut.daeut.partner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daeut.daeut.partner.dto.Review;
import com.daeut.daeut.partner.mapper.ReviewMapper;
import com.daeut.daeut.reservation.dto.Payments;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public List<Payments> getUserPayments(int userNo) {
        return reviewMapper.findPaymentsByUserNo(userNo);
    }

    @Override
    public void saveReview(Review review) {
        reviewMapper.insertReview(review);
    }
}