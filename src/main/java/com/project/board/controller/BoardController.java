package com.project.board.controller;

import com.project.board.dto.BoardDto;
import com.project.board.dto.BoardRequestDto;
import com.project.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@Slf4j

public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping("/feed")
    public List<BoardDto.BoardReadResponseDto> getBoards(){
        return boardService.getBoards();
    }

    @PostMapping("/feed")
    public BoardDto.BoardResponseDto createBoard(@RequestBody BoardRequestDto.requestDto requestDto){
        return boardService.createBoard(requestDto);
    }

    @GetMapping("/feed/{id}")
    public BoardDto.BoardReadResponseDto getSelectBoards(@PathVariable Long id){
        return boardService.getSelectBoards(id);
    }


    @PutMapping("/feed/{id}")
    public List<BoardDto.BoardReadResponseDto> updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto.requestDto requestDto){
        log.info("controller : " + requestDto.getPwd());
        return boardService.updateBoard(id, requestDto);
    }

    @DeleteMapping("/feed/{id}")
    public String deleteMemo(@PathVariable Long id, @RequestBody BoardRequestDto.requestDto requestDto){
        return boardService.deleteBoard(id, requestDto);
    }
}
