package com.daeut.daeut.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
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

	@GetMapping("/reservation/reservation")
	public String reservationlink() {
		return "/reservation/reservation";
	}


	@GetMapping("/reservationRead")
	public String reservationread() {
		return "/reservation/reservationRead";
	}

	
	@GetMapping("/reservationInsert")
	public String reservationinsert() {
		return "/reservation/reservationInsert";
	}

	// @GetMapping("/reservation/chat")
	// public String chat() {
	// 	return "/reservation/chat";
	// }

	// @GetMapping("/reservationInsert")
	// public String reservation() {
	// 	return "/reservation/reservationInsert";
	// }
	
	
    // @GetMapping("/chat")
    // public String main() {
    //     log.info("채팅 화면...");
    //     return "chat";
    // }

	// 결제관련
	@GetMapping("/paymentDone")
    public String paymentDone() {
        return "/reservation/paymentDone";
    }

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