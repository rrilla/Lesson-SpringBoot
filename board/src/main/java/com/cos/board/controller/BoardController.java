package com.cos.board.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cos.board.dto.BoardSaveRequestDto;
import com.cos.board.dto.ImageSaveDto;
import com.cos.board.model.Board;
import com.cos.board.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/saveForm")
	public String saveForm() {
		return "saveForm";
	}
	
	@PostMapping("/save")
	@ResponseBody
	public String save(@RequestBody BoardSaveRequestDto dto) {
		boardService.글쓰기(dto);
		return "ok";
	}
	
	@GetMapping({"","/","/list"})	//중괄호 입력시 안에 여러주소 배열로 선언가능
	public String list(Model model) {
		model.addAttribute("boards", boardService.글목록());
		return "list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable int id, Model model) throws Exception {
		model.addAttribute("board", boardService.글상세보기(id));
		return "detail";
	}
	
	@DeleteMapping("board/{id}")
	@ResponseBody
	public String delete(@PathVariable int id) {
		boardService.글삭제하기(id);
		return "ok";
	}
	
	@PutMapping("board/{id}")
	@ResponseBody
	public String update(@PathVariable int id, @RequestBody BoardSaveRequestDto dto) {
		boardService.글수정하기(id, dto);
		return "ok";
	}
	
	@GetMapping("test")
	@ResponseBody
	public Board test() {
		System.out.println(boardService.테스트("test"));
		return boardService.테스트("test");
	}
	
	@PostMapping("/imageUpload")
	@ResponseBody
	public ImageSaveDto imageUpload(@RequestParam("file") MultipartFile multipartFile) {
		return boardService.이미지저장(multipartFile);
	}

}
