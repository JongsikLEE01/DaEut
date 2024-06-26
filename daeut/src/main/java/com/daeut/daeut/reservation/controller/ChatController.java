package com.daeut.daeut.reservation.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.reservation.dto.ChatRooms;
import com.daeut.daeut.reservation.dto.Chats;
import com.daeut.daeut.reservation.service.ChatRoomService;
import com.daeut.daeut.reservation.service.ChatService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ChatController {

    /*
     * SimpMessageSendingOperations : 메세징 지원을 위해 제공되는 템플릿 클래스
     *                                서버에서 클라이언트로 메세지를 push하는데 사용
     *                                메세지 전송, 특정 주제에 대한 메세지 전송, 즉 브로드캐스트 가능
     *                                사용자 전송, 특정 사용자의 queue에 메세지를 전송해 1:1 구현
     */
    @Autowired
    private SimpMessageSendingOperations template;

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRoomService chatRoomService;

    @GetMapping("/reservation/chat")
    public String goToChatRoom(@RequestParam("roomNo") String roomNo, Model model, HttpSession session) throws Exception {
        Users user = (Users) session.getAttribute("user");
        ChatRooms chatRooms = chatRoomService.select(roomNo);
        int partnerNo = chatRooms.getPartnerNo();

        List<Chats> chatList = chatService.selectByRoomNo(roomNo);

        model.addAttribute("chatRooms", chatRooms);
        model.addAttribute("partnerNo", partnerNo);
        model.addAttribute("user", user);
        model.addAttribute("roomNo", roomNo);
        model.addAttribute("chatList", chatList);
        return "reservation/chat";
    }

    @MessageMapping("/chat/sendMessage")
    public void sendMessage(@Payload Chats chat) throws Exception {
        chatService.insert(chat);

        log.info("chat? {}", chat);

        template.convertAndSend("/sub/chat/" + chat.getRoomNo(), chat);        
    }
}
