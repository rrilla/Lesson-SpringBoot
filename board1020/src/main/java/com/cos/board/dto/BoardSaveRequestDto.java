package com.cos.board.dto;
import com.cos.board.model.Board;

import lombok.Data;

@Data
public class BoardSaveRequestDto {
	private String title;
	private String content;
	
	public static Board toEntity(BoardSaveRequestDto dto) {
		Board board = Board.builder()
				.title(dto.getTitle())
				.content(dto.getContent())
				.build();
		return board;
	}
}
