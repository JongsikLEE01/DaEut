package com.daeut.daeut.tip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daeut.daeut.tip.dto.Reply;
import com.daeut.daeut.tip.service.ReplyService;

import lombok.extern.slf4j.Slf4j;




@Slf4j
@Controller
@RequestMapping("/reply")
public class ReplyController {
    
    @Autowired
    private ReplyService replyService;

    // 댓글 목록
    @GetMapping("/{boardNo}")
    public String list(@PathVariable("boardNo") int boardNo
                      ,Model model) throws Exception {
        List<Reply> replyList = replyService.listByBoardNo(boardNo);

        model.addAttribute("replyList", replyList);
        
        return "reply/list";
    }

    // 댓글 등록
    @PostMapping("/insert")
    public ResponseEntity<Reply> insert(Reply reply) {
        log.info("reply : " + reply);
        return new ResponseEntity<>(reply, HttpStatus.OK);
    }
    
    // @PostMapping("")
    // public ResponseEntity<String> insert(Reply reply) throws Exception {
    //     log.info("reply : " + reply);

    //     int result = replyService.insert(reply);
    //     if( result > 0 ) {
    //         return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
    //     }
    //     return new ResponseEntity<>("FAIL", HttpStatus.OK);
    // }

    
    
    // 댓글 수정
    @PutMapping("")
    public ResponseEntity<String> update(@RequestBody Reply reply) throws Exception {
        int result = replyService.update(reply);
        if( result > 0 ) {
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }
        return new ResponseEntity<>("FAIL", HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/{no}")
    public ResponseEntity<String> delete(
                                @PathVariable("no") int no) throws Exception {
        int result = replyService.delete(no);
        if( result > 0 ) {
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }
        return new ResponseEntity<>("FAIL", HttpStatus.OK);
    }                                
}
