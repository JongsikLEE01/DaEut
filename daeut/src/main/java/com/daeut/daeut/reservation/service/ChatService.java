package com.daeut.daeut.reservation.service;

import java.util.List;

import com.daeut.daeut.reservation.dto.Chats;

public interface ChatService {
    public List<Chats> list() throws Exception;
    public Chats select(int chatNo) throws Exception;
    public List<Chats> selectByRoomNo(String roomNo) throws Exception;
    public int insert(Chats chats) throws Exception;
    public int update(Chats chats) throws Exception;
    public int delete(int chatNo) throws Exception;
}
