package com.daeut.daeut.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daeut.daeut.reservation.dto.Cart;
import com.daeut.daeut.reservation.service.CartService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/cart")
public class CartController {
    
    @Autowired
    private CartService cartService;

    @GetMapping("/list")
    public String getCartList(@RequestParam("userNo") int userNo, Model model)throws Exception {
        List<Cart> cartList = cartService.cartList(userNo);

        model.addAttribute("cartList", cartList);
        return "/cart/list";
    }

    @PostMapping("/add")
    public String cartInsert(@RequestBody Cart cart)throws Exception {
        cart.setCartAmount(1);
        log.info("장바구니 정보: {}", cart);
        int result = cartService.cartInsert(cart);

        int serviceNo = cart.getServiceNo();

        if (result == 0) {
            log.info("장바구니 등록 실패...");
            return "redirect:/cart/add";
        }

        log.info("장바구니 등록 성공...");
        return "redirect:/reservation/reservationRead?serviceNo="+serviceNo;
    }

    @DeleteMapping("/delete/{cartNo}")
    public String cartDelete(@PathVariable("cartNo") int cartNo)throws Exception {
        int result = cartService.cartDelete(cartNo);

        if (result == 0) {
            log.info("장바구니 삭제 실패...");
            return "redirect:/cart/list";
        }

        log.info("장바구니 삭제 성공...");
        return "redirect:/cart/list";
    }


    @DeleteMapping("/deleteAll")
    public String cartDeleteAll(@RequestParam("userNo") int userNo)throws Exception {
        int result = cartService.cartDeleteAll(userNo);

        if (result == 0) {
            log.info("장바구니 전체삭제 실패...");
            return "redirect:/cart/list";
        }

        log.info("장바구니 전체삭제 성공...");
        return "redirect:/cart/list";
    }
}