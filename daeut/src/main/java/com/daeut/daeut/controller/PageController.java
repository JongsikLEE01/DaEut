package com.daeut.daeut.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;



@Slf4j
@Controller
// @RequestMapping("/index")
public class PageController {

	@GetMapping("/{path}")
	public String getMethodName(@PathVariable("path") String path ) {
		return path;
	}

	@GetMapping("/reservation")
	public String reservation() {
		return "/reservatrion/reservationInsert";
	}
	
	
    // @GetMapping("/chat")
    // public String main() {
    //     log.info("채팅 화면...");
    //     return "chat";
    // }
    
}