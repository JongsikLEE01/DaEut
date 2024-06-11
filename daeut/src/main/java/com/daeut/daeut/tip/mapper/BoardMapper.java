package com.daeut.daeut.tip.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.daeut.daeut.tip.dto.Option2;
import com.daeut.daeut.main.dto.Files;
import com.daeut.daeut.main.dto.Page;
import com.daeut.daeut.tip.dto.Board;

@Mapper
public interface BoardMapper {
    
    // 게시글 목록
    public List<Board> list(@Param("page") Page page
                          , @Param("option") Option2 option
                          , @Param("sort") String sort) throws Exception;

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

    // 게시글 데이터 개수 조회
    public int count(@Param("option") Option2 option) throws Exception;

    // 게시글 목록 - [검색]
    public List<Board> search(@Param("option") Option2 option) throws Exception;

    // 조회수 증가
    public int view(int boardNo) throws Exception;
    
    // public List<Board> findTop5ByBoardViews();

    public void incrementBoardLike(int boardNo) throws Exception;
}
