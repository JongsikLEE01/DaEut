package com.daeut.daeut.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
// @RequestMapping("/index")
public class PageController {

	@GetMapping("/{path}")
	public String getMethodName(@PathVariable("path") String path ) {
		return path;
	}

	// @GetMapping("/reservationInsert")
	// public String reservation() {
	// 	return "/reservation/reservationInsert";
	// }
	
	
    // @GetMapping("/chat")
    // public String main() {
    //     log.info("채팅 화면...");
    //     return "chat";
    // }
    
}