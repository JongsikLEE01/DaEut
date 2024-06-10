package com.daeut.daeut.auth.service;

import com.daeut.daeut.auth.dto.Review;
import com.daeut.daeut.reservation.dto.Payments;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ReviewService {

    public List<Payments> getUserPayments(int userNo);

    public Payments getPaymentDetails(int paymentNo);
    
    public void saveReview(Review review);

    public List<Review> getReviewByServiceNo(int serviceNo);
}
