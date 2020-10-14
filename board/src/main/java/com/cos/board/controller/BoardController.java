package com.cos.board.controller;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.board.config.ex.MyArgsNotFound;
import com.cos.board.dto.BoardSaveRequestDto;
import com.cos.board.model.Board;
import com.cos.board.repository.BoardRepository;
import com.cos.board.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@GetMapping("/saveForm")
	public String saveForm() {
		return "saveForm";
	}
	
	@PostMapping("/save")
	public String save(BoardSaveRequestDto dto) {
		
		boardService.글쓰기(dto);
		
		return "redirect:/list";
	}
	
//	@PostMapping("/save2")
//	public String save2(Board board) {
//		System.out.println(board.getTitle());
//		System.out.println(board.getContent());
//		
//		boardRepository.save(board);
//		
//		return "list";
//	}
	
	@GetMapping({"","/","/list"})	//중괄호 입력시 안에 여러주소 배열로 선언가능
	public String list(Model model) {
		model.addAttribute("boards", boardService.글목록());
		return "list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable int id, Model model) throws Exception {
		//못찾으면 어떻게 할지 
		//Optional<Board> opBoard = boardRepository.findById(id);
		//Board board = boardRepository.findById(id).orElse(new Board());
		
//		Board board = boardRepository.findById(id).orElseGet(new Supplier<Board>() {
//			@Override
//			public Board get() {
//				System.out.println("id값의 데이터가 엄슴.");
//				return new Board();
//			}
//		});
		
//		Board board = boardRepository.findById(id).orElseGet(
//			() -> {
//				System.out.println("id값의 데이터가 엄슴.");
//				return new Board();
//			}
//		);
		
//		Board board = boardRepository.findById(id)
//				.orElseThrow(new Supplier<Exception>() {
//
//				@Override
//				public Exception get() {
//					return new Exception("id값 잘 못 들어옴.");
//				}
//			});
		
		model.addAttribute("board", boardService.글상세보기(id));
		
		return "detail";
	}
	
	@DeleteMapping("board/{id}")
	@ResponseBody
	public String delete(@PathVariable int id) {
		boardService.글삭제하기(id);
		return "ok";
	}
	
	@PutMapping("board/{id}")	//
	@ResponseBody
	public String update(@PathVariable int id, @RequestBody BoardSaveRequestDto dto) {
		boardService.글수정하기(id, dto);
		return "ok";
	}
	
}
