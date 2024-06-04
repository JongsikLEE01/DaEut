package com.daeut.daeut.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daeut.daeut.reservation.dto.ChatRooms;
import com.daeut.daeut.reservation.dto.Payments;
import com.daeut.daeut.reservation.mapper.ChatRoomMapper;

@Service
public class ChatRoomServiceImpl implements ChatRoomService{

    @Autowired
    private ChatRoomMapper chatRoomMapper;

    @Override
    public List<ChatRooms> list() throws Exception {
        return chatRoomMapper.list();
    }

    @Override
    public ChatRooms select(int roomNo) throws Exception {
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
        return chatRoomMapper.insert(chatRooms);
    }

    @Override
    public int update(ChatRooms chatRooms) throws Exception {
        return chatRoomMapper.update(chatRooms);
    }

    @Override
    public int delete(int roomNo) throws Exception {
        return chatRoomMapper.delete(roomNo);
    }

    // 있으면 update, 없으면 insert
    @Override
    public int merge(ChatRooms chatRooms) throws Exception {
        if( chatRooms == null || select(chatRooms.getRoomNo()) == null ) 
            return insert(chatRooms);

        return update(chatRooms);
    } 
}
