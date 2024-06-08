package com.daeut.daeut.partner.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.daeut.daeut.partner.dto.Review;
import com.daeut.daeut.reservation.dto.Payments;

@Mapper
public interface ReviewMapper {
    List<Payments> findPaymentsByUserNo(@Param("userNo") int userNo);
    void insertReview(Review review);
}
