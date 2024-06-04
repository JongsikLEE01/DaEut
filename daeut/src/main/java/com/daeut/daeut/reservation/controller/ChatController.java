package com.daeut.daeut.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.daeut.daeut.reservation.dto.Chats;
import com.daeut.daeut.reservation.service.ChatRoomService;
import com.daeut.daeut.reservation.service.ChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ChatController {
    
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRoomService chatRoomService;


    // @MessageMapping("/chat.message")
    // public Chats sendMessage(@RequestBody Chats chats) throws Exception {
    //     // 메시지 저장
    //     chatService.insert(chats);

    //     // 메시지를 해당 채팅방 구독자들에게 전송
    //     messagingTemplate.convertAndSend("/sub/chatroom/" + chats.getRoomNo(), chats);
    //     // return ResponseEntity.ok("메시지 전송 완료");
    // }
}