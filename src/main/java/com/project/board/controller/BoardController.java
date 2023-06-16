package com.project.board.controller;

import com.project.board.dto.BoardRequestDto;
import com.project.board.dto.BoardResponseDto;
import com.project.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping("/feed")
    public List<BoardResponseDto> getBoards(){
        return boardService.getBoards();
    }

    @PostMapping("/feed")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto){
        return boardService.createBoard(requestDto);
    }

    @PutMapping("/feed/{id}")
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto){

        return boardService.updateBoard(id, requestDto);
    }

    @DeleteMapping("/feed/{id}")
    public Long deleteMemo(@PathVariable Long id){
        return boardService.deleteBoard(id);
    }
}
