package com.daeut.daeut.tip.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Set;
import java.util.HashSet;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daeut.daeut.main.dto.Files;
import com.daeut.daeut.tip.dto.Option2;
import com.daeut.daeut.tip.dto.Reply;
import com.daeut.daeut.main.dto.Page;
import com.daeut.daeut.main.service.FileService;
import com.daeut.daeut.tip.dto.Board;
import com.daeut.daeut.tip.service.BoardService;
import com.daeut.daeut.tip.service.ReplyService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@Slf4j
@Controller
@RequestMapping("/tip")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private FileService filesService;

    @Autowired
    private ReplyService replyService;

    // 게시글 목록 조회 화면
    @GetMapping("/index")
    public String index(Model model, Page page, Option2 option) throws Exception {

        page = new Page(page.getPage(), 9, page.getCount(), page.getTotal());

        List<Board> boardList = boardService.list(page, option);

        // 각 게시글에 댓글수 추가
        for (Board board : boardList) {
            int replyCount = replyService.countByBoardNo(board.getBoardNo());
            board.setReplyCount(replyCount);
        }

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

        // 댓글 수 설정
        int replyCount = replyService.countByBoardNo(boardNo);
        board.setReplyCount(replyCount);

        // 현재 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = null;
        Set<String> currentRoles = new HashSet<>(); // 제네릭 타입을 명시
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        currentUserId = userDetails.getUsername();
        currentRoles = AuthorityUtils.authorityListToSet(userDetails.getAuthorities());
    }

        boolean isWriter = currentUserId != null && currentUserId.equals(board.getUserId());
        boolean isAdmin = currentRoles.contains("ROLE_ADMIN");

        model.addAttribute("isWriter", isWriter);
        model.addAttribute("isAdmin", isAdmin);

        file.setParentTable("board");
        file.setParentNo(boardNo);
        List<Files> fileList = filesService.listByParent(file);
        log.info(fileList.toString());

        // 댓글 목록 조회
        List<Reply> replyList = replyService.listByBoardNo(boardNo);

        model.addAttribute("board", board);
        model.addAttribute("fileList", fileList);
        model.addAttribute("replyList", replyList); // 댓글 목록 추가

        return "tip/tipRead";
    }

    // 게시글 등록 화면
    @GetMapping("/tipInsert")
    public String tipInsert() {

        return "tip/tipInsert";
    }

    // 게시글 등록 처리
    @PostMapping("/tipInsert")
    public String tipInsertPro(Board board, @RequestParam("userNo") int userNo) throws Exception {
        board.setUserNo(userNo);
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

        // 현재 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = authentication.getName();

        // 게시글 작성자와 현재 사용자가 같은지 확인
        if (!board.getUserId().equals(currentUserId) && !isAdmin(authentication)) {
            throw new IllegalAccessException("수정 권한이 없습니다.");
        }

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

    private boolean isAdmin(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
    }


    // 게시글 수정 처리
    @PostMapping("/tipUpdate")
    public String tipUpdatePro(Board board) throws Exception {
        // 현재 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = authentication.getName();

        // 게시글 작성자와 현재 사용자가 같은지 확인
        Board existingBoard = boardService.select(board.getBoardNo());
        if (!existingBoard.getUserId().equals(currentUserId) && !isAdmin(authentication)) {
            throw new IllegalAccessException("수정 권한이 없습니다.");
        }

        int result = boardService.update(board);

        if (result > 0) {
            return "redirect:/tip/index";
        }
        int no = board.getBoardNo();
        return "redirect:/tip/tipUpdate?no=" + no + "&error";   
    }

    
    // 게시글 삭제 처리
    @PostMapping("/tipDelete")
    public String tipDeletePro(@RequestParam("boardNo") int boardNo) throws Exception {
        // 현재 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = authentication.getName();
    
        // 게시글 작성자와 현재 사용자가 같은지 확인
        Board existingBoard = boardService.select(boardNo);
        if (!existingBoard.getUserId().equals(currentUserId) && !isAdmin(authentication)) {
            throw new IllegalAccessException("삭제 권한이 없습니다.");
        }
    
        int result = boardService.delete(boardNo);
        if (result > 0) {
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
    
    // 좋아요 수 증가
    @PutMapping("/{boardNo}/like")
    @ResponseBody
    public Map<String, Object> incrementBoardLike(@PathVariable int boardNo, @RequestParam int userNo, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Integer> likedBoards = (List<Integer>) session.getAttribute("likedBoards");
            if (likedBoards == null) {
                likedBoards = new ArrayList<>();
            }

            if (likedBoards.contains(boardNo)) {
                response.put("success", false);
                response.put("message", "이미 추천한 게시글입니다.");
            } else {
                boardService.incrementBoardLike(boardNo, userNo);
                likedBoards.add(boardNo);
                session.setAttribute("likedBoards", likedBoards);
                response.put("success", true);
                response.put("message", "게시글 추천 완료!");
            }
        } catch (Exception e) {
            log.error("추천 증가 중 오류 발생", e);
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }

    @PostMapping("/reply/insert")
    public String insertReply(@RequestParam("boardNo") int boardNo, @RequestParam("replyContent") String replyContent, @RequestParam("userNo") int userNo) throws Exception {
        Reply reply = new Reply();
        reply.setBoardNo(boardNo);
        reply.setReplyContent(replyContent);
        reply.setUserNo(userNo);

        replyService.insert(reply);

        return "redirect:/tip/tipRead?no=" + boardNo;
    }

    @PostMapping("/reply/insertReply")
    public String insertReplyReply(@RequestParam("boardNo") int boardNo, @RequestParam("replyContent") String replyContent, @RequestParam("userNo") int userNo, @RequestParam("parentNo") int parentNo) throws Exception {
        Reply reply = new Reply();
        reply.setBoardNo(boardNo);
        reply.setReplyContent(replyContent);
        reply.setUserNo(userNo);
        reply.setParentNo(parentNo);

        replyService.insert(reply);

        return "redirect:/tip/tipRead?no=" + boardNo;
    }
    
}
