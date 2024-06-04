package com.daeut.daeut.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daeut.daeut.reservation.dto.Chats;
import com.daeut.daeut.reservation.mapper.ChatMapper;

@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    private ChatMapper chatMapper;

    @Override
    public List<Chats> list() throws Exception {
        return chatMapper.list();
    }

    @Override
    public Chats select(int chatNo) throws Exception {
        return chatMapper.select(chatNo);
    }

    @Override
    public List<Chats> selectByRoomNo(String roomNo) throws Exception {
        return chatMapper.selectByRoomNo(roomNo);
    }

    @Override
    public int insert(Chats chats) throws Exception {
        return chatMapper.insert(chats);
    }

    @Override
    public int update(Chats chats) throws Exception {
        return chatMapper.update(chats);
    }

    @Override
    public int delete(int chatNo) throws Exception {
        return chatMapper.delete(chatNo);
    }   
}