package com.daeut.daeut.tip.service;

import java.util.List;

import com.daeut.daeut.main.dto.Files;
import com.daeut.daeut.tip.dto.Board;

public interface BoardService {
    
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

    // 조회수 증가
    public int view(int boardViews) throws Exception;
    
    public Files SelectThumbnail(int boardNo) throws Exception;

    public List<Files> SelectFiles(int boardNo) throws Exception;
}
