package com.daeut.daeut.reservation.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.reservation.dto.ChatRooms;
import com.daeut.daeut.reservation.dto.Chats;
import com.daeut.daeut.reservation.service.ChatRoomService;
import com.daeut.daeut.reservation.service.ChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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

    // // 채팅으로 이동
    // @GetMapping("/userChatRoom")
    // public String goChatRoom(Model model, HttpSession session) throws Exception{
    //     Users user = (Users) session.getAttribute("user");
    //     int userNo = user.getUserNo();

    //     List<ChatRooms> chatRoomList = chatRoomService.selectByUserNo(userNo);
    //     log.info("All chatRoomList {}", chatRoomList);

    //     model.addAttribute("chatRoomList", chatRoomList);
    //     return "user/userChatRoom";
    // }
    

    /**
     * 채팅방 생성
     * @writer JSLEE
     * @param chatRoom
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping("/chatRoom")
    public ResponseEntity<String> createChatRoom(@RequestBody ChatRooms chatRoom, Model model) throws Exception{    
        log.info("--------CharController----------");
        log.info("chatRoom {}", chatRoom);

        int result = chatRoomService.merge(chatRoom);

        if(result == 0)
            return new ResponseEntity<>("FAIL",HttpStatus.INTERNAL_SERVER_ERROR);
        
        return new ResponseEntity<>("SUCCESS",HttpStatus.OK);
    }
    
    /**
     * 메세지 전송
     * 클라이언트에서 /app/chat.sendMessage 경로로 메시지를 보낼 때 호출
     * @writer JSLEE
     * @param chat
     * @throws Exception
     */
    @MessageMapping("/chat/sendMessage")
    public String sendMessage(Chats chat) throws Exception {
        chatService.insert(chat);
        log.info("chat? "+chat);

        // 클라이언트에게 메시지 전송
        messagingTemplate.convertAndSend("/topic/public", chat);

        return "redirect:/reservation/chat";
    }
    

    // @GetMapping("/chat/messages")
    // public List<Chats> getMessages(@RequestBody ChatRooms chatRooms) throws Exception{
    //     String roomNo = chatRooms.getRoomNo();
    //     List<Chats> messages = chatService.selectByRoomNo(roomNo);

    //     return messages;
    // }
}