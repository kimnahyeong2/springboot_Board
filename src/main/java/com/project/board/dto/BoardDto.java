package com.project.board.dto;

import com.project.board.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

public class BoardDto {
    @Getter
    public static class BoardResponseDto{
        private Long id;
        private String title;
        private String username;
        private String contents;
        private String pwd;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public BoardResponseDto(Board board){
            this.id = board.getId();
            this.title = board.getTitle();
            this.username = board.getUsername();
            this.contents = board.getContents();
            this.pwd = board.getPwd();
            this.createdAt = board.getCreatedAt();
            this.modifiedAt = board.getModifiedAt();
        }
    }

    @Getter
    public static class BoardReadResponseDto {
        private String title;
        private String username;
        private String contents;
        private LocalDateTime createdAt;

        public BoardReadResponseDto(Board board){
            this.title = board.getTitle();
            this.username = board.getUsername();
            this.contents = board.getContents();
            this.createdAt = board.getCreatedAt();
        }
    }
}
