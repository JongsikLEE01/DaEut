package com.daeut.daeut.partner.service;

import com.daeut.daeut.reservation.dto.Payments;
import com.daeut.daeut.partner.dto.Review;

import java.util.List;

public interface ReviewService {
    List<Payments> getUserPayments(int userNo);
    void saveReview(Review review);
}
