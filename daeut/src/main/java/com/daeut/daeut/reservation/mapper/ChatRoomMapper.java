package com.daeut.daeut.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.daeut.daeut.reservation.dto.ChatRooms;

@Mapper
public interface ChatRoomMapper {
    public List<ChatRooms> list() throws Exception;
    public ChatRooms select(int roomNo) throws Exception;
    public ChatRooms selectByUserNo(int userNo) throws Exception;
    public ChatRooms selectByPartnerNo(int partnerNo) throws Exception;
    public int insert(ChatRooms chatRooms) throws Exception;
    public int update(ChatRooms chatRooms) throws Exception;
    public int delete(int roomNo) throws Exception;
}