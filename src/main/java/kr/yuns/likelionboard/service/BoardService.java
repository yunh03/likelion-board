package kr.yuns.likelionboard.service;

import kr.yuns.likelionboard.data.dto.*;
import kr.yuns.likelionboard.data.entity.Board;

import java.util.List;

public interface BoardService {
    List<Board> postList();
    BoardResponseDto getDetail(Long id);
    BoardResponseDto savePost(BoardDto boardDto);
    void deletePostWithPassword(BoardDeleteDto boardDeleteDto) throws Exception;
    BoardUpdateResponseDto updatePost(BoardUpdateDto boardUpdateDto) throws Exception;
    void deletePost(Long id) throws Exception;
}
