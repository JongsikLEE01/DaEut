package com.daeut.daeut.tip.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daeut.daeut.main.dto.Files;
import com.daeut.daeut.tip.dto.Option2;
import com.daeut.daeut.main.dto.Page;
import com.daeut.daeut.main.service.FileService;
import com.daeut.daeut.tip.dto.Board;
import com.daeut.daeut.tip.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/tip")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private FileService filesService;

    // 게시글 목록 조회 화면
    @GetMapping("/index")
    public String index(Model model, Page page, Option2 option) throws Exception {
        List<Board> boardList = boardService.list(page, option);

        log.info("page : " + page);
        log.info("option : " + option);

        model.addAttribute("boardList", boardList);
        model.addAttribute("page", page);
        model.addAttribute("option", option);

        List<Option2> optionList = new ArrayList<Option2>();
        optionList.add(new Option2("전체", 0));
        optionList.add(new Option2("제목", 1));
        optionList.add(new Option2("내용", 2));
        optionList.add(new Option2("제목+내용", 3));
        optionList.add(new Option2("작성자", 4));
        model.addAttribute("optionList", optionList);
        
        return "tip/index";
    }

    // 게시글 조회 화면
    @GetMapping("/tipRead")
    public String read(@RequestParam("no") int boardNo, Files file, Model model) throws Exception {
        Board board = boardService.select(boardNo);

        int view = boardService.view(boardNo);

        log.info("------------------------------------");
        log.info("-----------------/tip/tipRead-------------------");
        log.info(board.toString());
        
        file.setParentTable("board");
        file.setParentNo(boardNo);
        List<Files> fileList = filesService.listByParent(file);
        log.info(fileList.toString());

        model.addAttribute("board", board);
        model.addAttribute("fileList", fileList);

        return "tip/tipRead";
    }

    // 게시글 등록 화면
    @GetMapping("/tipInsert")
    public String tipInsert() {

        return "tip/tipInsert";
    }

    // 게시글 등록 처리
    @PostMapping("/tipInsert")
    public String tipInsertPro(Board board) throws Exception {
        log.info(board.toString());
        int result = boardService.insert(board);
        if (result > 0) {
            return "redirect:/tip/index";
        }
        return "redirect:/tip/tipInsert?error";
    }

    // 게시글 수정 화면
    @GetMapping("/tipUpdate")
    public String tipUpdate(@RequestParam("no") int boardNo, Model model, Files file) throws Exception {
        // 데이터 요청
        Board board = boardService.select(boardNo);

        log.info("------------------------------------");
        log.info("-----------------/tip/tipUpdate-------------------");
        log.info(board.toString());

        // 파일 목록 요청
        file.setParentTable("board");
        file.setParentNo(boardNo);
        List<Files> fileList = filesService.listByParent(file);
        log.info(fileList.toString());

        // 모델 등록
        model.addAttribute("board", board);
        model.addAttribute("fileList", fileList);

        return "/tip/tipUpdate";
    }

    // 게시글 수정 처리
    @PostMapping("/tipUpdate")
    public String tipUpdatePro(Board board) throws Exception {

        int result = boardService.update(board);

        if( result > 0 ) {
            return "redirect:/tip/index";
        }
        int no = board.getBoardNo();
        return "redirect:/tip/tipUpdate?no=" + no + "&error";   
    }
    
    // 게시글 삭제 처리
    @PostMapping("/tipDelete")
    public String tipDeletePro(@RequestParam("boardNo") int boardNo) throws Exception {
        int result = boardService.delete(boardNo);
        if( result > 0 ) {

            Files file = new Files();
            file.setParentTable("board");
            file.setParentNo(boardNo);
            filesService.deleteByParent(file);
            
            return "redirect:/tip/index";
        }
        return "redirect:/tip/tipUpdate?no=" + boardNo + "&error";
        
    }

    // 조회수 기준 상위 5개 게시글 조회 화면
    // @GetMapping("/top5")
    // public String getTop5Tips(Model model) throws Exception {
    //     List<Board> boardList = boardService.getTop5BoardsByBoardViews();
    //     model.addAttribute("boardList", boardList);
    //     return "tip/index";
    // }
    
    
}
