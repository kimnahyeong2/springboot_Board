package com.project.board.service;

import com.project.board.dto.BoardRequestDto;
import com.project.board.dto.BoardResponseDto;
import com.project.board.entity.Board;
import com.project.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    public List<BoardResponseDto> getBoards() {
        return boardRepository.findAllByOrderByModifiedAtDesc().stream().map(BoardResponseDto::new).toList();
    }

    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);

        Board saveBoard = boardRepository.save(board);

        BoardResponseDto boardResponseDto = new BoardResponseDto(saveBoard);

        return boardResponseDto;
    }

    @Transactional
    public Long updateBoard(Long id, BoardRequestDto requestDto) {
        Board board = findBoard(id);
        board.update(requestDto);
        return id;
    }

    public Long deleteBoard(Long id){
        Board board = findBoard(id);
        boardRepository.delete(board);
        return id;
    }

    private Board findBoard(Long id){
        return boardRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("존재하지 않습니다")
        );
    }
}
