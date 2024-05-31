package com.daeut.daeut.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daeut.daeut.reservation.dto.Cart;
import com.daeut.daeut.reservation.mapper.CartMapper;


@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartMapper cartMapper;

    // 목록
    @Override
    public List<Cart> cartList(int userNo) throws Exception {
        List<Cart> cartList = cartMapper.cartList(userNo);
        return cartList;
    }

    // 삽입
    @Override
    public int cartInsert(Cart cart) throws Exception {
        int result = cartMapper.cartInsert(cart);
        return result;
    }

    // 수정
    @Override
    public int cartUpdate(Cart cart) throws Exception {
        int result = cartMapper.cartUpdate(cart);
        return result;
    }

    // 삭제
    @Override
    public int cartDelete(int cartNo) throws Exception {
        int result = cartMapper.cartDelete(cartNo);
        return result;
    }

    // 모두 삭제
    @Override
    public int cartDeleteAll(int userNo) throws Exception {
        int result = cartMapper.cartDeleteAll(userNo);
        return result;
    }
}