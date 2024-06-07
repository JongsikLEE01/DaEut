package com.daeut.daeut.reservation.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.auth.service.UserService;
import com.daeut.daeut.partner.dto.Partner;
import com.daeut.daeut.partner.service.PartnerService;
import com.daeut.daeut.reservation.dto.Cart;
import com.daeut.daeut.reservation.dto.Services;
import com.daeut.daeut.reservation.mapper.CartMapper;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private UserService userService;

    // 목록
    @Override
    public List<Cart> cartList(int userNo) throws Exception {
        List<Cart> cartList = cartMapper.cartList(userNo);
        return cartList;
    }

    // 삽입
    @Override
    public int cartInsert(Cart cart) throws Exception {
        int serviceNo = cart.getServiceNo();
        Services services = reservationService.serviceSelect(serviceNo);
        Partner partner = partnerService.selectByPartnerNo(services.getPartnerNo());
        int partnerNo = partner.getUserNo();
        Users users = userService.findUserById(partnerNo);
        log.info(users.toString());

        cart.setPartnerName(users.getUserName());

        log.info(cart.toString());

        return cartMapper.cartInsert(cart);
    }

    // 수정
    @Override
    public int cartUpdate(Cart cart) throws Exception {
        int result = cartMapper.cartUpdate(cart);
        return result;
    }

    // 모두 삭제
    @Override
    public int cartDeleteAll(int userNo) throws Exception {
        int result = cartMapper.cartDeleteAll(userNo);
        return result;
    }

    // 선택삭제
    @Override
    public int cartDeleteSelected(List<Integer> cartNos) throws Exception {
        String deleteNoList = cartNos.stream()
                            .map(s -> s.toString())
                            .collect(Collectors.joining(","));  // "1,2,3,4"
        log.info("삭제할 카트번호들 : " + deleteNoList);
        int result = cartMapper.cartDeleteSelected(deleteNoList);           
        return result;
    }

    @Override
    public int cartDelete(int cartNo) throws Exception {
        return cartDelete(cartNo);
    }
}