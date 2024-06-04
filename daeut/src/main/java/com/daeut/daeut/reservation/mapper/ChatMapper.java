package com.daeut.daeut.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.daeut.daeut.reservation.dto.Chats;

@Mapper
public interface ChatMapper {
    public List<Chats> list() throws Exception;
    public Chats select(int chatNo) throws Exception;
    public Chats selectByRoomNo(int roomNo) throws Exception;
    public int insert(Chats chats) throws Exception;
    public int update(Chats chats) throws Exception;
    public int delete(int chatNo) throws Exception;
}
