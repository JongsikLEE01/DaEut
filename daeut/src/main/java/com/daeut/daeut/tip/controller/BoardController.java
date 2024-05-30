package com.daeut.daeut.tip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daeut.daeut.tip.dto.Board;
import com.daeut.daeut.tip.dto.Files;
import com.daeut.daeut.tip.service.BoardService;
import com.daeut.daeut.tip.service.FilesService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequestMapping("/tip")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private FilesService filesService;

    @GetMapping("/index")
    public String index(Model model) throws Exception {
        List<Board> boardList = boardService.list();
        model.addAttribute("boardList", boardList);
        return "tip/index";
    }

    @GetMapping("/tipRead")
    public String read(@RequestParam("no") int boardNo, Model model) throws Exception {
        Board board = boardService.select(boardNo);
        int view = boardService.view(boardNo);

        // file.setParentTable("board");
        // file.setParentNo(boardNo);
        // List<Files> fileList = filesService.listByParent(file);

        model.addAttribute("board", board);
        // model.addAttribute("fileList", fileList);

        return "tip/tipRead";
    }

    @GetMapping("/tipInsert")
    public String tipInsert() {

        return "tip/tipInsert";
    }

    @PostMapping("/tipInsert")
    public String tipInsertPro(Board board) throws Exception {
        // log.info(board.toString());
        int result = boardService.insert(board);
        if (result > 0) {
            return "redirect:/tip/index";
        }
        return "redirect:/tip/tipInsert?error";
    }

    @GetMapping("/tipUpdate")
    public String tipUpdate(@RequestParam("no") int boardNo, Model model, Files file) throws Exception {
        Board board = boardService.select(boardNo);

        // file.setParentTable("board");
        file.setParentNo(boardNo);
        // List<Files> fileList = filesService.listByParent(file);

        model.addAttribute("board", board);
        // model.addAttribute("fileList", fileList);

        return "/tip/tipUpdate";
    }

    @PostMapping("/tipUpdate")
    public String tipUpdatePro(Board board) throws Exception {
        int result = boardService.update(board);
        if( result > 0 ) {
            return "redirect:/tip/index";
        }
        int no = board.getBoardNo();
        return "redirect:/tip/tipUpdate?no=" + no + "&error";   
    }
    
    @PostMapping("/tipDelete")
    public String tipDeletePro(@RequestParam("boardNo") int boardNo) throws Exception {
        int result = boardService.delete(boardNo);
        if( result > 0 ) {

            // Files file = new Files();
            // file.setParentTable("board");
            // file.setParentNo(boardNo);
            // filesService.deleteByParent(file);
            
            return "redirect:/tip/index";
        }
        return "redirect:/tip/tipUpdate?no=" + boardNo + "&error";
        
    }
    
    
}
