package com.daeut.daeut.tip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.daeut.daeut.main.dto.Files;
import com.daeut.daeut.tip.dto.Option2;
import com.daeut.daeut.main.dto.Page;
import com.daeut.daeut.main.service.FileService;
import com.daeut.daeut.tip.dto.Board;
import com.daeut.daeut.tip.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private FileService fileService;

    @Autowired
    private ReplyService replyService;

    // 게시글 목록 조회
    @Override
    public List<Board> list(Page page, Option2 option) throws Exception {
        int total = boardMapper.count(option);
        page.setTotal(total);

        List<Board> boardList = boardMapper.list(page, option);
        return boardList;
    }

    // 게시글 조회
    @Override
    public Board select(int boardNo) throws Exception {
        Board board = boardMapper.select(boardNo);
        return board;
    }

    // 게시글 등록
    @Override
    public int insert(Board board) throws Exception {
        int result = boardMapper.insert(board);

        String parentTable = "board";
        int parentNo = boardMapper.maxPk();

        MultipartFile thumbnailFile = board.getThumbnail();

        if( thumbnailFile != null && !thumbnailFile.isEmpty() ) {
            Files thumbnail = new Files();
            thumbnail.setFile(thumbnailFile);
            thumbnail.setParentTable(parentTable);
            thumbnail.setFileNo(parentNo);
            thumbnail.setFileCode(1);
            fileService.upload(thumbnail);
        }

        List<MultipartFile> fileList = board.getFile();
        if( !fileList.isEmpty() ) {
            for(MultipartFile file : fileList) {
                log.info("file : " + file.getOriginalFilename());
                if( file.isEmpty() ) continue;

                Files uploadFile = new Files();
                uploadFile.setParentTable(parentTable);
                uploadFile.setParentNo(parentNo);
                uploadFile.setFile(file);
                uploadFile.setFileCode(0);
                fileService.upload(uploadFile);
            }
        }

        return result;
    }

    // 게시글 수정
    @Override
    public int update(Board board) throws Exception {
        int result = boardMapper.update(board);
        return result;
    }

    // 게시글 삭제
    @Override
    public int delete(int boardNo) throws Exception {
        int result = boardMapper.delete(boardNo);
        if( result > 0 ){
            result += replyService.deleteByBoardNo(boardNo);
        }
        return result;
    }

    @Override
    public int view(int boardNo) throws Exception {
        log.info(boardNo + "번 글 조회수 증가");
        return boardMapper.view(boardNo);
    }

    @Override
    public List<Board> search(Option2 option) throws Exception {
        List<Board> boardList = boardMapper.search(option);
        return boardList;
    }

    // @Override
    // public List<Board> getTop5BoardsByBoardViews() {
    //     return boardMapper.findTop5ByBoardViews();
    // }
}
