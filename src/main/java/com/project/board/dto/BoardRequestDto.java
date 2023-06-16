package com.project.board.dto;

import lombok.Getter;

public class BoardRequestDto {
    @Getter
    public static class requestDto{
        private String title;
        private String username;
        private String contents;
        private String pwd;
    }
}
