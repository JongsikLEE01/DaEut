package com.daeut.daeut.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservation")
public class PageController {

	// 결제 이동
	@GetMapping("/payment")
    public String payment() {
        return "/reservation/payment";
    }

	// 결제 성공
	@GetMapping("/paymentDone")
    public String paymentDone() {
        return "/reservation/paymentDone";
    }

	// 결제 실패
    @GetMapping("/paymentFalse")
    public String paymentFalse() {
        return "/reservation/paymentFalse";
    }
}