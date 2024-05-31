package com.daeut.daeut.tip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.daeut.daeut.main.dto.Files;
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

    @Override
    public List<Board> list() throws Exception {
        List<Board> boardList = boardMapper.list();
        return boardList;
    }

    @Override
    public Board select(int boardNo) throws Exception {
        Board board = boardMapper.select(boardNo);
        return board;
    }

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

    @Override
    public int update(Board board) throws Exception {
        int result = boardMapper.update(board);

        String parentTable = "board";
        int parentNo = boardMapper.maxPk();

        MultipartFile thumbnailFile = board.getThumbnail();
        if(thumbnailFile != null && !thumbnailFile.isEmpty()) {
            Files thumbnail = new Files();
            thumbnail.setFile(thumbnailFile);
            thumbnail.setParentTable(parentTable);
            thumbnail.setParentNo(parentNo);
            thumbnail.setFileCode(0);
            fileService.upload(thumbnail);
        }

        List<MultipartFile> fileList = board.getFile();
        if(fileList != null && !fileList.isEmpty()) {
            for(MultipartFile file : fileList) {
                if(file.isEmpty()) continue;

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

    @Override
    public int delete(int boardNo) throws Exception {
        int result = boardMapper.delete(boardNo);
        return result;
    }

    @Override
    public int view(int boardNo) throws Exception {
        return boardMapper.view(boardNo);
    }

}
