package com.daeut.daeut.tip.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.daeut.daeut.main.dto.Files;
import com.daeut.daeut.tip.dto.Board;

@Mapper
public interface BoardMapper {
    
    // 게시글 목록
    public List<Board> list() throws Exception;

    // 게시글 조회
    public Board select(int boardNo) throws Exception;

    // 게시글 등록
    public int insert(Board board) throws Exception;

    // 게시글 수정
    public int update(Board board) throws Exception;

    // 게시글 삭제
    public int delete(int boardNo) throws Exception;

    // 게시글 번호(기본키) 최댓값
    public int maxPk() throws Exception;

    // 조회수 증가
    public int view(int boardNo) throws Exception;
    
}
