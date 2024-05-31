package com.daeut.daeut.reservation.controller;

import com.daeut.daeut.reservation.dto.Cart;
import com.daeut.daeut.reservation.service.CartService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 장바구니 목록 조회
     * @writer jslee
     * @param userNo
     * @return
     */
    @GetMapping("/{userNo}")
    public ResponseEntity<?> getCartList(@PathVariable("userNo") int userNo) {
        List<Cart> cartList;
        HttpHeaders headers = new HttpHeaders();

        try {
            cartList = cartService.cartList(userNo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("장바구니 목록 조회 중 에러가 발생했습니다.", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(cartList, headers, HttpStatus.OK);
    }

    /**
     * 장바구니 추가
     * @writer jslee
     * @param cart
     * @return
     */
    @PostMapping
    public ResponseEntity<String> addCart(@RequestBody Cart cart) {
        int result = 0;
        
        try {
            // 장바구니 추가 요청
            result = cartService.cartInsert(cart);
        } catch (Exception e) {
            // 추가 실패
            e.printStackTrace();
            return new ResponseEntity<>("FAIL",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // 추가 성공
        return new ResponseEntity<>("SUCCESS",HttpStatus.OK);
    }

    /**
     * 장바구니 선택 삭제
     * @writer jslee
     * @param cartNo
     * @return
     */
    @DeleteMapping("/{cartNo}")
    public ResponseEntity<String> removeCart(@PathVariable("cartNo") int cartNo) {
        int result;
        
        try {
            // 장바구니 추가 요청
            result = cartService.cartDelete(cartNo);
        } catch (Exception e) {
            // 추가 실패
            e.printStackTrace();
            return new ResponseEntity<>("FAIL",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // 추가 성공
        return new ResponseEntity<>("SUCCESS",HttpStatus.OK);
    }

    /**
     * 장바구니 전체 삭제
     * @writer jslee
     * @param userNo
     * @return
     */
    @DeleteMapping("/user/{userNo}")
    public ResponseEntity<String> removeAllCarts(@PathVariable("userNo") int userNo) {
        int result;
        
        try {
            // 장바구니 추가 요청
            result = cartService.cartDeleteAll(userNo);
        } catch (Exception e) {
            // 추가 실패
            e.printStackTrace();
            return new ResponseEntity<>("FAIL",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // 추가 성공
        return new ResponseEntity<>("SUCCESS",HttpStatus.OK);
    }
}
