package com.daeut.daeut.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

import com.daeut.daeut.reservation.dto.Chats;
import com.daeut.daeut.reservation.service.ChatRoomService;
import com.daeut.daeut.reservation.service.ChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatController {
    
    /*
     * SimpMessageSendingOperations : 메세징 지원을 위해 제공되는 템플릿 클래스
     *                                서버에서 클라이언트로 메세지를 push하는데 사용
     *                                메세지 전송, 특정 주제에 대한 메세지 전송, 즉 브로드캐스트 가능
     *                                사용자 전송, 특정 사용자의 queue에 메세지를 전송해 1:1 구현
     */
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRoomService chatRoomService;

    /**
     * 채팅방 생성
     * 클라이언트에서 /app/chat.addUser 경로로 메시지를 보낼 때 호출
     * @writer JSLEE
     * @param chat
     * @return
     * @throws Exception
     */
    @MessageMapping("/chat.addUser")
    public Chats addUser(Chats chat) throws Exception {
        chatRoomService.insert(chat.getChatRooms());
        return chat;
    }
    
    /**
     * 메세지 전송
     * 클라이언트에서 /app/chat.sendMessage 경로로 메시지를 보낼 때 호출
     * @writer JSLEE
     * @param chat
     * @throws Exception
     */
    @MessageMapping("/chat.sendMessage")
    public void sendMessage(Chats chat) throws Exception {
        int userNo = chat.getUserNo();

        chatService.insert(chat);
        log.info("chat? "+chat);

        // 클라이언트에게 메시지 전송
        messagingTemplate.convertAndSend("/topic/public", chat);
    }
}