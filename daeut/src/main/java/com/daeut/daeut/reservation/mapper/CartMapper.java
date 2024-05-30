package com.daeut.daeut.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.daeut.daeut.reservation.dto.Cart;

@Mapper
public interface CartMapper {
    public List<Cart> cartList(int userNo) throws Exception;
    public int cartInsert(Cart cart) throws Exception;
    public int cartDelete(int cartNo) throws Exception;
    public int cartDeleteAll(int userNo) throws Exception;
}