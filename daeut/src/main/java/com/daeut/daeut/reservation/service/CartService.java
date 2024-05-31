package com.daeut.daeut.reservation.service;

import java.util.List;

import com.daeut.daeut.reservation.dto.Cart;

public interface CartService {
    public List<Cart> cartList(int userNo) throws Exception;
    public int cartInsert(Cart cart) throws Exception;
    public int cartUpdate(Cart cart) throws Exception;
    public int cartDelete(int cartNo) throws Exception;
    public int cartDeleteAll(int userNo) throws Exception;
}
