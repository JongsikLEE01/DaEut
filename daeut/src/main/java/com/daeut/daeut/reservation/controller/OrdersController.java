package com.daeut.daeut.reservation.controller;

import javax.servlet.http.HttpSession;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.reservation.dto.OrderItems;
import com.daeut.daeut.reservation.dto.Orders;
import com.daeut.daeut.reservation.service.OrderItemService;
import com.daeut.daeut.reservation.service.OrdersService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Controller("orders")
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrderItemService orderItemService;

    // TODO : 주문 서비스 연동 필요

    /**
     * 주문하기
     * @return
     */
    @GetMapping("")
    public String orders() {
        return "/orders/index";
    }

    /**
     * 주문 등록
     * @param param
     * @return
     */
    @PostMapping("")
    public String orderPost(Orders orders,
                            HttpSession session,
                            @RequestParam List<String> serviceNo, 
                            @RequestParam List<Integer> quantity) throws Exception {
        log.info("::::::::: 주문 등록 - orderPost() ::::::::::");
        log.info("productId : " + serviceNo);
        log.info("quantity : " + quantity);

        // 주문 등록
        int result = ordersService.insert(orders);

        log.info("신규 등록된 주문ID : " + orders.getOrdersNo() );
        if( result > 0 ) {
            return "redirect:/orders/" + orders.getOrdersNo();
        }
        // TODO : 주문 실패시 어디로 가는게 좋을지? - 장바구니? 주문내역? 상품목록?
        else {
            return "redirect:/orders";
        }
    }
    
/**
     * 주문 완료
     * @param model
     * @param session
     * @param ordersId
     * @return
     * @throws Exception 
     */
    @GetMapping("/success")
    public String orderSuccess(Model model
                            //   ,Payments payments
                              ,HttpSession session
                              ,@RequestParam("orderNo") String orderNo) throws Exception {

        // payments.setOrdersId(orderId);
        // payments.setStatus(PaymentsStatus.PAID);
        // paymentsService.merge(payments);
        
        // payments = paymentsService.selectByOrdersId(orderId);
        // log.info(":::::::::::::::::::: payments ::::::::::::::::::::");
        // log.info(payments.toString());

        Orders order = ordersService.select(orderNo);
        log.info(":::::::::::::::::::: orders ::::::::::::::::::::");
        // log.info(payments.toString());

        model.addAttribute("order", order);
        return "/orders/success";
    }

    /**
     * 주문 실패 
     * @param model
     * @param session
     * @param ordersId
     * @return
     * @throws Exception 
     */
    @GetMapping("/fail")
    public String orderFail(Model model
                            //   ,Payments payments
                              ,HttpSession session
                              ,@RequestParam("orderNo") String orderNo
                              ,@ModelAttribute String errorMsg) throws Exception {                    
        // payments.setOrdersId(orderId);
        // payments.setStatus(PaymentsStatus.PAID);
        // paymentsService.insert(payments);
        
        // ⭐ 결제 실패 시, 결제 상태 PENDING 으로 변경
        // payments = paymentsService.selectByOrdersId(orderId);
        // payments.setStatus(PaymentsStatus.PENDING);
        // paymentsService.merge(payments);
        // log.info(":::::::::::::::::::: payments ::::::::::::::::::::");
        // log.info(payments.toString());

        Orders order = ordersService.select(orderNo);
        log.info(":::::::::::::::::::: orders ::::::::::::::::::::");
        // log.info(payments.toString());

        log.info("[결제 실패] 에러 메시지 : " + errorMsg);

        // model.addAttribute("payments", payments);
        model.addAttribute("order", order);
        return "/orders/fail";
    }

    /**
     * 주문/결제  
     * - ➡ 결제하기
     * @param model
     * @param orderId
     * @return
     * @throws Exception
     */
    @GetMapping("/{orderId}")
    public String checkout(Model model
                          ,HttpSession session
                          ,@PathVariable("orderNo") String orderNo) throws Exception {
        
        // 로그인 사용자
        Users user = (Users) session.getAttribute("user");
        // 주문 정보
        Orders order = ordersService.select(orderNo);
        // 주문 항목 정보
        List<OrderItems> orderItems = orderItemService.listByOrderId(orderNo);
        
        if( order == null ) return "redirect:/orders?error";
        log.info(":::::::::::::::::::: order ::::::::::::::::::::");
        log.info(order.toString());
        log.info(":::::::::::::::::::: order items ::::::::::::::::::::");
        log.info(orderItems.toString());


        model.addAttribute("order", order);
        model.addAttribute("orderItems", orderItems);
        return "/orders/checkout";
    }
}
