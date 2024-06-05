package com.daeut.daeut.reservation.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.reservation.dto.ChatRooms;
import com.daeut.daeut.reservation.dto.Chats;
import com.daeut.daeut.reservation.service.ChatRoomService;
import com.daeut.daeut.reservation.service.ChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
// @RequiredArgsConstructor
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

    // MessageMapping 을 통해 webSocket 로 들어오는 메시지를 발신 처리한다.
    // 이때 클라이언트에서는 /pub/chat/message 로 요청하게 되고 이것을 controller 가 받아서 처리한다.
    // 처리가 완료되면 /sub/chat/room/roomId 로 메시지가 전송된다.
    // @MessageMapping("/chat/enterUser")
    // public void enterUser(@Payload Chats chat, SimpMessageHeaderAccessor headerAccessor) {
        

    //     // 반환 결과를 socket session 에 저장
    //     headerAccessor.getSessionAttributes().put("userUUID", userUUID);
    //     headerAccessor.getSessionAttributes().put("roomNo", chat.getRoomNo());

    //     chat.setMessage(chat.getSender() + " 님 입장!!");
    //     template.convertAndSend("/sub/chat/room/" + chat.getRoomId(), chat);
    // }

    /**
     * 채팅방으로 이동
     * @param roomNo
     * @param model
     * @param session
     * @return
     * @throws Exception
     */
    @GetMapping("/reservation/chat")
    public String goToChatRoom(@RequestParam("roomNo") String roomNo, Model model, HttpSession session) throws Exception {
        Users user = (Users) session.getAttribute("user");
        ChatRooms chatRooms = chatRoomService.select(roomNo);
        int partnerNo = chatRooms.getPartnerNo();

        List<Chats> chatList = chatService.selectByRoomNo(roomNo);

        model.addAttribute("partnerNo", partnerNo);
        model.addAttribute("user", user);
        model.addAttribute("roomNo", roomNo);
        model.addAttribute("chatList", chatList);
        return "reservation/chat";
    }

    
    /**
     * 메세지 전송
     * @param chat
     * @return
     * @throws Exception
     */
    @MessageMapping("/chat/sendMessage")
    public String sendMessage(Chats chat) throws Exception {
        chatService.insert(chat);

        // 클라이언트에게 메시지 전송
        template.convertAndSend("/chat", chat);
        return "redirect:/reservation/chat?roomNo="+chat.getRoomNo(); // 채팅창으로 리다이렉트
    }

    /**
     * 메세지 저장
     * @param chat
     * @return
     */
    @PostMapping("/chat/saveMessage")
    public String saveMessage(@RequestBody Chats chat) {
        try {
            chatService.insert(chat);
            return "redirect:/reservation/chat?roomNo="+chat.getRoomNo();
        } catch (Exception e) {
            log.error("Error saving message", e);
            return "redirect:/reservation/chat?roomNo="+chat.getRoomNo();
        }
    }
}