package com.cos.board.controller;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.board.config.ex.MyArgsNotFound;
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
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Board> boards =  boardRepository.findAll();
		model.addAttribute("boards", boards);
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
		
		Board board = boardRepository.findById(id)
				.orElseThrow(() -> 
					 new MyArgsNotFound("id값 잘못오ㅗㅅ다")
				);
		
		model.addAttribute("board", board);
		
		return "detail";
	}
	
	@DeleteMapping("board/{id}")
	@ResponseBody
	public String delete(@PathVariable int id) {
		boardRepository.deleteById(id);
		return "ok";
	}
	
}
