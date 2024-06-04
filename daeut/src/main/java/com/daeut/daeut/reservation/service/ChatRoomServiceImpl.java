package com.daeut.daeut.reservation.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daeut.daeut.reservation.dto.ChatRooms;
import com.daeut.daeut.reservation.mapper.ChatRoomMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChatRoomServiceImpl implements ChatRoomService{

    @Autowired
    private ChatRoomMapper chatRoomMapper;

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
        ChatRooms checkChatRooms = select(chatRooms.getRoomNo());
        
        if (checkChatRooms != null) {
            return update(chatRooms);
        } else {
            String roomNo = UUID.randomUUID().toString();
            chatRooms.setRoomNo(roomNo);

            return chatRoomMapper.insert(chatRooms);
        }
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
        log.info("------chatRoom ServiceImpl--------");
        log.info("chatRoom? {}", chatRoom);

        if( chatRoom == null || select(chatRoom.getRoomNo()) == null ) 
            return insert(chatRoom);

        return update(chatRoom);
    } 
}
