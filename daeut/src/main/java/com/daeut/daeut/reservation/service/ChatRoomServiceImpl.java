package com.daeut.daeut.reservation.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.auth.service.UserService;
import com.daeut.daeut.partner.dto.Partner;
import com.daeut.daeut.partner.service.PartnerService;
import com.daeut.daeut.reservation.dto.ChatRooms;
import com.daeut.daeut.reservation.mapper.ChatRoomMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChatRoomServiceImpl implements ChatRoomService{

    @Autowired
    private ChatRoomMapper chatRoomMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private PartnerService partnerService;

    @Override
    public List<ChatRooms> list() throws Exception {
        return chatRoomMapper.list();
    }

    @Override
    public ChatRooms select(String roomNo) throws Exception {
        return chatRoomMapper.select(roomNo);
    }

    @Override
    public List<ChatRooms> selectByUserNo(int userNo) throws Exception {
        return chatRoomMapper.selectByUserNo(userNo);
    }

    @Override
    public List<ChatRooms> selectByPartnerNo(int partnerNo) throws Exception {
        return chatRoomMapper.selectByPartnerNo(partnerNo);
    }

    @Override
    public int insert(ChatRooms chatRooms) throws Exception {
        String roomNo = UUID.randomUUID().toString();
        chatRooms.setRoomNo(roomNo);

        // 파트너 이름 가져오기
        Partner partner = partnerService.selectByPartnerNo(chatRooms.getPartnerNo());
        int partnerNo = partner.getUserNo();
        Users pUsers = userService.findUserById(partnerNo);

        // 유저 이름 가져오기
        Users user = userService.findUserById(chatRooms.getUserNo());
        chatRooms.setTitle(pUsers.getUserName() + "님과 " + user.getUserName() + "님이 대화중입니다");

        return chatRoomMapper.insert(chatRooms);
    }

    @Override
    public int update(ChatRooms chatRooms) throws Exception {
        return chatRoomMapper.update(chatRooms);
    }

    @Override
    public int delete(String roomNo) throws Exception {
        return chatRoomMapper.delete(roomNo);
    }

    // 있으면 update, 없으면 insert
    @Override
    public int merge(ChatRooms chatRoom) throws Exception {
        int userNo = chatRoom.getUserNo();
        int partnerNo = chatRoom.getPartnerNo();

        List<ChatRooms> chatRoomList = selectByUserNo(userNo);

        // partnerNo, userNo가 중복된 채팅룸 생성 X
        for (ChatRooms cRooms : chatRoomList) {
            int uNo = cRooms.getUserNo();
            int pNo = cRooms.getPartnerNo();
            if(uNo == userNo && pNo == partnerNo){
                return update(chatRoom);
            }
        }

        return insert(chatRoom);
    } 
}
