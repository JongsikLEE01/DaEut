package com.daeut.daeut.tip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @GetMapping("/list")
    public ResponseEntity<List<Reply>> getReplies(@RequestParam("boardNo") int boardNo) throws Exception {
        List<Reply> replies = replyService.listByBoardNo(boardNo);
        return new ResponseEntity<>(replies, HttpStatus.OK);
    }

    // 댓글 등록
    // @PostMapping("/insert")
    // public ResponseEntity<Reply> insert(Reply reply) {
    //     log.info("reply : " + reply);
    //     return new ResponseEntity<>(reply, HttpStatus.OK);
    // }
    
    // @PostMapping("/insert")
    // public ResponseEntity<String> insert(@RequestBody Reply reply) throws Exception {
    //     log.info("reply : " + reply);

    //     int result = replyService.insert(reply);
    //     if( result > 0 ) {
    //         return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
    //     }
        
    //     return new ResponseEntity<>("FAIL", HttpStatus.OK);
    // }

    // 댓글 등록
    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestBody Reply reply) throws Exception {

        String currentUserId = getCurrentUserId();
            if (currentUserId != null) {
                reply.setUserId(currentUserId);
            } else {
                return new ResponseEntity<>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
            }

        log.info("reply : " + reply);

        // 데이터 요청
        int result = replyService.insert(reply);
        if( result > 0 ) {
            // 데이터 처리 성공
            return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("FAIL", HttpStatus.OK);
    }
    
    // 댓글 수정
    @PutMapping("")
    public ResponseEntity<String> update(@RequestBody Reply reply) throws Exception {
        String currentUserId = getCurrentUserId();
        if (currentUserId == null) {
            return new ResponseEntity<>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
        }

        log.info("Trying to update reply: {}", reply);
        Reply existingReply = replyService.select(reply.getReplyNo());
        log.info("Existing reply from DB: {}", existingReply);

        if (!existingReply.getUserId().equals(currentUserId) && !isAdmin()) {
            throw new IllegalAccessException("수정 권한이 없습니다.");
        }
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
        String currentUserId = getCurrentUserId();
        if (currentUserId == null) {
            return new ResponseEntity<>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
        }

        log.info("Trying to delete reply with ID: {}", no);
        Reply existingReply = replyService.select(no);
        log.info("Existing reply from DB: {}", existingReply);

        if (!existingReply.getUserId().equals(currentUserId) && !isAdmin()) {
            throw new IllegalAccessException("삭제 권한이 없습니다.");
        }     
        int result = replyService.delete(no);
        if( result > 0 ) {
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }
        return new ResponseEntity<>("FAIL", HttpStatus.OK);
    }      
    
    private boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
    }

    private String getCurrentUserId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }
    return null;
    }
}
