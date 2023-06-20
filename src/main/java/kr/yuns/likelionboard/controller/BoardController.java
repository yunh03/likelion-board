package kr.yuns.likelionboard.controller;

import kr.yuns.likelionboard.data.dto.*;
import kr.yuns.likelionboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("list", boardService.postList());
        return "home";
    }

    @GetMapping("/new")
    public String newPost() {
        return "new";
    }

    @GetMapping("/view")
    public String postDetail(Model model, Long id) {
        model.addAttribute("post", boardService.getDetail(id));
        return "view";
    }

    @ResponseBody
    @PostMapping("/new")
    public String newPostDo(BoardDto boardDto) {
        BoardResponseDto boardResponseDto = boardService.savePost(boardDto);
        System.out.println(boardResponseDto);
        String message = "<script>alert('게시글 업로드가 완료되었습니다.');location.href='/';</script>";
        return message;
    }

    @GetMapping("/modify/delete/{id}")
    public String postDelete(@PathVariable("id") Long id, Model model) {
        model.addAttribute("post", id);
        return "delete";
    }

    @ResponseBody
    @PostMapping("/modify/delete/{id}")
    public String postDeleteWithPassword(@PathVariable("id") Long id, BoardDeleteDto boardDeleteDto) throws Exception {
        boardDeleteDto.setId(id);
        try {
            boardService.deletePostWithPassword(boardDeleteDto);
            String message = "<script>alert('게시글 삭제가 정상적으로 처리되었습니다.');location.href='/';</script>";
            return message;
        } catch (Exception e) {
            String message = "<script>alert('비밀번호가 일치하지 않습니다. 메인 화면으로 되돌아갑니다.');location.href='/';</script>";
            return message;
        }
    }

    @GetMapping("/modify/{id}")
    public String postModify(@PathVariable("id") Long id, Model model) {
        model.addAttribute("post", boardService.getDetail(id));
        return "modify";
    }

    @ResponseBody
    @PostMapping("/modify/{id}")
    public String postModifyDo(@PathVariable("id") Long id, BoardUpdateDto boardUpdateDto) throws Exception {
        boardUpdateDto.setId(id);
        try {
            boardService.updatePost(boardUpdateDto);
            String message = "<script>alert('게시글 수정이 완료되었습니다.');location.href='/';</script>";
            return message;
        } catch (Exception e) {
            String message = "<script>alert('비밀번호가 일치하지 않습니다. 메인 화면으로 되돌아갑니다.');location.href='/';</script>";
            return message;
        }
    }
}
