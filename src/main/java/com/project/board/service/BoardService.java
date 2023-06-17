package com.project.board.service;

import com.project.board.dto.BoardDto;
import com.project.board.dto.BoardRequestDto;
import com.project.board.entity.Board;
import com.project.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;
    @Autowired
    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    public List<BoardDto.BoardReadResponseDto> getBoards() {
        return boardRepository.findAllByOrderByCreatedAtDesc().stream().map(BoardDto.BoardReadResponseDto::new).toList();
    }

    public BoardDto.BoardResponseDto createBoard(BoardRequestDto.requestDto requestDto) {
        Board board = new Board(requestDto);

        Board saveBoard = boardRepository.save(board);

        BoardDto.BoardResponseDto boardResponseDto;
        boardResponseDto = new BoardDto.BoardResponseDto(saveBoard);

        return boardResponseDto;
    }
    public BoardDto.BoardReadResponseDto getSelectBoards(Long id) {
        Board board = findBoard(id);
        return ResponseEntity.ok().body(new BoardDto.BoardReadResponseDto(board)).getBody();
    }

    @Transactional
    public List<BoardDto.BoardReadResponseDto> updateBoard(Long id, BoardRequestDto.requestDto requestDto) {
        Board board = findBoard(id);
        boolean check = comparePwd(requestDto, board);
        if(!check){
            return null;
        }
        else{
            board.update(requestDto);
            return boardRepository.findAllByOrderByCreatedAtDesc().stream().map(BoardDto.BoardReadResponseDto::new).toList();
        }
    }

    public String deleteBoard(Long id, BoardRequestDto.requestDto requestDto){
        Board board = findBoard(id);
        boolean check = comparePwd(requestDto, board);
        if(!check){
            return "비밀번호가 다릅니다:)";
        }
        else{
            boardRepository.delete(board);
            return "삭제 성공!";
        }

    }

    private Board findBoard(Long id){
        return boardRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("존재하지 않습니다")
        );
    }

    private boolean comparePwd(BoardRequestDto.requestDto requestDto, Board board){
        log.info("원래 비번 : " + board.getPwd());
        log.info("입력 비번 : " + requestDto.getPwd());
        return Objects.equals(board.getPwd(), requestDto.getPwd());
    }


}
