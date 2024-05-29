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
import org.springframework.web.multipart.MultipartFile;

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
        List<Files> files = filesService.listByParent(boardNo);
        model.addAttribute("board", board);
        model.addAttribute("files", files);
        return "tip/tipRead";
    }

    @GetMapping("/tipInsert")
    public String tipInsert() {
        return "tip/tipInsert";
    }

    @PostMapping("/tipInsert")
    public String tipInsertPro(Board board, @RequestParam("files") List<MultipartFile> files) throws Exception {
        int result = boardService.insert(board);
        if (result > 0) {
            filesService.upload("board", board.getBoardNo(), files);
            return "redirect:/tip/index";
        }
        return "redirect:/tip/tipInsert?error";
    }
}
