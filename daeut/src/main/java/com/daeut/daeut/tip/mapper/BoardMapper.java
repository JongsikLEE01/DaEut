package com.daeut.daeut.tip.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.daeut.daeut.tip.dto.Board;

@Mapper
public interface BoardMapper {
    
    public List<Board> list() throws Exception;

    public Board select(int boardNo) throws Exception;

    public int insert(Board board) throws Exception;

    public int update(Board board) throws Exception;

    public int delete(int boardNo) throws Exception;

    public int maxPk() throws Exception;

    public int view(int boardNo) throws Exception;
    
}
