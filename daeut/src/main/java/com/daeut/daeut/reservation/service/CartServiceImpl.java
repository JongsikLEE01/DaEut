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

    @Override
    public List<Cart> cartList(int userNo) throws Exception {
        // 목록
        List<Cart> cartList = cartMapper.cartList(userNo);
        return cartList;
    }

    @Override
    public int cartInsert(Cart cart) throws Exception {
        // 카트추가
        int result = cartMapper.cartInsert(cart);
        return result;
    }

    @Override
    public int cartDelete(int cartNo) throws Exception {
        // 선택삭제
        int result = cartMapper.cartDelete(cartNo);
        return result;
    }

    @Override
    public int cartDeleteAll(int userNo) throws Exception {
        // 전체삭제
        int result = cartMapper.cartDeleteAll(userNo);
        return result;
    }
    
}
