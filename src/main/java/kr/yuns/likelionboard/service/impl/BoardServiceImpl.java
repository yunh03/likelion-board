package kr.yuns.likelionboard.service.impl;

import kr.yuns.likelionboard.data.dto.*;
import kr.yuns.likelionboard.data.entity.Board;
import kr.yuns.likelionboard.data.repository.BoardRepository;
import kr.yuns.likelionboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public List<Board> postList() {
        return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public BoardResponseDto getDetail(Long id) {
        Board board = boardRepository.findById(id).get();

        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setId(board.getId());
        boardResponseDto.setTitle(board.getTitle());
        boardResponseDto.setContents(board.getContents());
        boardResponseDto.setAuthor(board.getAuthor());
        boardResponseDto.setCreatedAt(board.getCreatedAt());

        return boardResponseDto;
    }

    @Override
    public BoardResponseDto savePost(BoardDto boardDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime currentDateTime = LocalDateTime.now();
        String formattedDateTime = currentDateTime.format(formatter);

        Board board = new Board();
        board.setTitle(boardDto.getTitle());
        board.setContents(boardDto.getContents());
        board.setCreatedAt(formattedDateTime);
        board.setAuthor(boardDto.getAuthor());
        board.setPassword(boardDto.getPassword());

        Board savedPost = boardRepository.save(board);

        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setId(savedPost.getId());
        boardResponseDto.setTitle(savedPost.getTitle());
        boardResponseDto.setContents(savedPost.getContents());
        boardResponseDto.setCreatedAt(savedPost.getCreatedAt());
        boardResponseDto.setAuthor(savedPost.getAuthor());

        return boardResponseDto;
    }

    @Override
    public void deletePostWithPassword(BoardDeleteDto boardDeleteDto) throws Exception {
        Board foundPost = boardRepository.findById(boardDeleteDto.getId()).get();
        if (foundPost == null) {
            throw new Exception("Post not found");
        }

        if(!foundPost.getPassword().equals(boardDeleteDto.getPassword())) {
            throw new Exception("Incorrect Password");
        }

        boardRepository.deleteById(boardDeleteDto.getId());
    }

    @Override
    public BoardUpdateResponseDto updatePost(BoardUpdateDto boardUpdateDto) throws Exception {
        Board foundPost = boardRepository.findById(boardUpdateDto.getId()).get();

        // Check if post exists
        if (foundPost == null) {
            throw new Exception("Post not found");
        }

        // Validate Password
        if (!foundPost.getPassword().equals(boardUpdateDto.getPassword())) {
            throw new Exception("Incorrect password");
        }

        foundPost.setTitle(boardUpdateDto.getTitle());
        foundPost.setContents(boardUpdateDto.getContents());

        Board updatedPost = boardRepository.save(foundPost);

        BoardUpdateResponseDto boardUpdateResponseDto = new BoardUpdateResponseDto();
        boardUpdateResponseDto.setId(updatedPost.getId());
        boardUpdateResponseDto.setTitle(updatedPost.getTitle());
        boardUpdateResponseDto.setContents(updatedPost.getContents());
        boardUpdateResponseDto.setAuthor(updatedPost.getAuthor());

        return boardUpdateResponseDto;
    }

    @Override
    public void deletePost(Long id) throws Exception {
        boardRepository.deleteById(id);
    }
}
