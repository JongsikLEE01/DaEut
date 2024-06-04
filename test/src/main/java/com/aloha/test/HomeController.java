package com.aloha.test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
public class HomeController {

    @GetMapping("")
    public String home() {
        return "index";
    }
    

    @PostMapping("/reply")
    public ResponseEntity<Reply> insert(@RequestBody Reply reply) {
        log.info("Reply : " + reply);

        return new ResponseEntity<>(reply, HttpStatus.OK);
    }
    
    
}
