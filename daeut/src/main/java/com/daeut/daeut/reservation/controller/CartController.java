package com.daeut.daeut.reservation.controller;

import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.auth.service.UserService;
import com.daeut.daeut.partner.dto.Partner;
import com.daeut.daeut.partner.service.PartnerService;
import com.daeut.daeut.reservation.dto.Cart;
import com.daeut.daeut.reservation.dto.Services;
import com.daeut.daeut.reservation.service.CartService;
import com.daeut.daeut.reservation.service.ReservationService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpSession;


@Slf4j
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    /**
     * 장바구니 목록 조회
     * @writer jslee
     * @param userNo
     * @return
     */
    @GetMapping("/{userNo}")
    public ResponseEntity<?> getCartList(@PathVariable int userNo) {
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
     * @throws Exception 
     */
    @PostMapping("/add")
    public ResponseEntity<String> addCart(@RequestBody Cart cart, HttpSession session) throws Exception {
        Users user = (Users) session.getAttribute("user");
        int serviceNo = cart.getServiceNo();
        Services service = reservationService.select(serviceNo);

        
        int pNo = service.getPartnerNo();
        Partner partner = partnerService.selectByPartnerNo(pNo);
        
        Users pUser = userService.findUserById(partner.getUserNo());
        log.info("pUser? {}", pUser);
        
        cart.setServiceNo(serviceNo);
        cart.setUserNo(user.getUserNo());
        cart.setCartAmount(1);
        cart.setPartnerName(pUser.getUserName());
        log.info("cart? {}", cart);

        // 장바구니에
        List<Cart> cartList = cartService.cartList(user.getUserNo());
        for (Cart originCart : cartList) {
            if(originCart.getServiceNo() == cart.getServiceNo())
                // 추가 실패
                return new ResponseEntity<>("FAIL",HttpStatus.INTERNAL_SERVER_ERROR);
        }


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
     * @param cartNos
     * @return
     * @throws Exception
     */
    @PostMapping("/delete")
    public String deleteCart(@RequestParam("cartNos") List<Integer> cartNos) throws Exception {
        log.info("cartNos :" + cartNos);
        
        int result = cartService.cartDeleteSelected(cartNos);
        return "redirect:/user/userCart";
    }
    

    /**
     * 장바구니 선택 삭제
     * @writer jslee
     * @param cartNo
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> removeSelectedCarts(@RequestBody List<Integer> cartNos) {
        int result;

        try {
            // 선택한 장바구니 항목들 삭제 요청
            result = cartService.cartDeleteSelected(cartNos);
        } catch (Exception e) {
            // 삭제 실패
            e.printStackTrace();
            return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // 삭제 성공
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    /**
     * 장바구니 전체 삭제
     * @writer jslee
     * @param userNo
     * @return
     */
    @DeleteMapping("/delete/{userNo}")
    public ResponseEntity<String> removeAllCarts(@PathVariable("userNo") int userNo) {
        int result;
        
        try {
            // 해당 사용자의 모든 장바구니 항목 삭제 요청
            result = cartService.cartDeleteAll(userNo);
        } catch (Exception e) {
            // 삭제 실패
            e.printStackTrace();
            return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    
        // 삭제 성공
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
}