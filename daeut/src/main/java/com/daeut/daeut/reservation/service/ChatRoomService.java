package com.daeut.daeut.reservation.service;

import java.util.List;

import com.daeut.daeut.reservation.dto.ChatRooms;

public interface ChatRoomService {
    public List<ChatRooms> list() throws Exception;
    public ChatRooms select(String roomNo) throws Exception;
    public List<ChatRooms> selectByUserNo(int userNo) throws Exception;
    public List<ChatRooms> selectByPartnerNo(int partnerNo) throws Exception;
    public int insert(ChatRooms chatRooms) throws Exception;
    public int update(ChatRooms chatRooms) throws Exception;
    public int delete(String roomNo) throws Exception;
    // merge : 없으면 insert, 있으면 update
    public int merge(ChatRooms chatRoom) throws Exception;
}
