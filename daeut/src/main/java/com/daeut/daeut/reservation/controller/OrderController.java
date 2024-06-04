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
import com.daeut.daeut.reservation.dto.PaymentStatus;
import com.daeut.daeut.reservation.dto.Payments;
import com.daeut.daeut.reservation.service.OrderItemService;
import com.daeut.daeut.reservation.service.OrderService;
import com.daeut.daeut.reservation.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Controller("orders")
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private PaymentService paymentService;

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
        log.info("serviceNo : " + serviceNo);
        log.info("quantity : " + quantity);

        Users user = (Users) session.getAttribute("user");
        orders.setUserNo(user.getUserNo());

        // 주문 등록
        int result = orderService.insert(orders);

        log.info("신규 등록된 주문ID : " + orders.getOrdersNo() );
        if( result > 0 ) {
            return "redirect:/orders/" + orders.getOrdersNo();
        }
        else {
            // 주문 실패시 상품목록
            return "redirect:/reservation/reservation";
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
                              ,Payments payments
                              ,HttpSession session
                              ,@RequestParam("ordersNo") String ordersNo) throws Exception {

        payments.setOrdersNo(ordersNo);
        payments.setPaymentMethod("card");
        payments.setStatus(PaymentStatus.PAID);
        paymentService.merge(payments);
        
        payments = paymentService.selectByOrdersNo(ordersNo);
        log.info(":::::::::::::::::::: payments ::::::::::::::::::::");
        log.info(payments.toString());

        Orders order = orderService.select(ordersNo);
        log.info(":::::::::::::::::::: orders ::::::::::::::::::::");
        log.info(payments.toString());

        model.addAttribute("order", order);
        return "/reservation/paymentDone";
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
                              ,Payments payments
                              ,HttpSession session
                              ,@RequestParam("ordersNo") String ordersNo
                              ,@ModelAttribute String errorMsg) throws Exception {                    
        payments.setOrdersNo(ordersNo);
        payments.setPaymentMethod("card");
        payments.setStatus(PaymentStatus.PAID);
        paymentService.insert(payments);
        
        // ⭐ 결제 실패 시, 결제 상태 PENDING 으로 변경
        payments = paymentService.selectByOrdersNo(ordersNo);
        payments.setStatus(PaymentStatus.PENDING);
        paymentService.merge(payments);
        log.info(":::::::::::::::::::: payments ::::::::::::::::::::");
        log.info(payments.toString());

        Orders order = orderService.select(ordersNo);
        log.info(":::::::::::::::::::: orders ::::::::::::::::::::");
        log.info(payments.toString());

        log.info("[결제 실패] 에러 메시지 : " + errorMsg);

        model.addAttribute("payments", payments);
        model.addAttribute("order", order);
        return "reservation/paymentFalse";
    }

    /**
     * 주문/결제  
     * - ➡ 결제하기
     * @param model
     * @param orderId
     * @return
     * @throws Exception
     */
    @GetMapping("/{ordersNo}")
    public String checkout(Model model
                          ,HttpSession session
                          ,@PathVariable("ordersNo") String ordersNo) throws Exception {
        
        // 로그인 사용자
        Users user = (Users) session.getAttribute("user");
        // 주문 정보
        Orders order = orderService.select(ordersNo);
        // 주문 항목 정보
        List<OrderItems> orderItems = orderItemService.listByOrderNo(ordersNo);
        
        if( order == null ) return "redirect:/orders?error";
        log.info(":::::::::::::::::::: order ::::::::::::::::::::");
        log.info(order.toString());
        log.info(":::::::::::::::::::: order items ::::::::::::::::::::");
        log.info(orderItems.toString());
        
        

        model.addAttribute("order", order);
        model.addAttribute("orderItems", orderItems);
        return "/reservation/payment";
    }
}
