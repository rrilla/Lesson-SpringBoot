package com.cos.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cos.board.dto.BoardSaveRequestDto;
import com.cos.board.model.Board;
import com.cos.board.repository.BoardRepository;



@Controller
public class BoardController {

	@Autowired
	private BoardRepository boardRepository;
	
	@GetMapping("/saveForm")
	public String saveForm() {
		return "saveForm";
	}
	
	@PostMapping("/save")
	public String save(BoardSaveRequestDto dto) {
		System.out.println(dto);
		
		Board boardEntity = BoardSaveRequestDto.toEntity(dto);
		boardRepository.save(boardEntity);
		
		return null;
	}
	
	@PostMapping("/save2")
	public String save2(Board board) {
		System.out.println(board.getTitle());
		System.out.println(board.getContent());
		
		boardRepository.save(board);
		
		return null;
	}
	
}
