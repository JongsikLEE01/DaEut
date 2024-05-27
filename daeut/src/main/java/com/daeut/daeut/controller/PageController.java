package com.daeut.daeut.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservation")
public class PageController {
	
	@GetMapping("/{path}")
	public String getMethodName(@PathVariable("path") String path ) {
		return path;
	}

	@GetMapping("/reservation")
	public String reservationlist() {
		return "/reservation/reservation";
	}

	@GetMapping("/chat")
	public String chat() {
		return "/reservation/chat";
	} 

	@GetMapping("/reservationRead")
	public String reservationread() {
		return "/reservation/reservationRead";
	}

	
	@GetMapping("/reservationInsert")
	public String reservationinsert() {
		return "/reservation/reservationInsert";
	}


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

    	// 팁
	@GetMapping("/tip")
    public String tip() {
        return "/tip/tip";
    }

    @GetMapping("/tipRead")
    public String tipRead() {
        return "/tip/tipRead";
    }

    @GetMapping("/tipInsert")
    public String tipInsert() {
        return "/tip/tipInsert";
    }

	// 파트너
    @GetMapping("/partnerMypage")
    public String partnerMypage() {
        return "/partner/partnerMypage";
    }

    @GetMapping("/partnerReview")
    public String partnerReview() {
        return "/partner/partnerReview";
    }

    @GetMapping("/partnerReservation")
    public String partnerReservation() {
        return "/partner/partnerReservation";
    }

    @GetMapping("/partnerReservationRead")
    public String partnerReservationRead() {
        return "/partner/partnerReservationRead";
    }

}

