package com.cos.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cos.board.config.auth.PrincipalDetails;
import com.cos.board.dto.BoardSaveRequestDto;
import com.cos.board.dto.ImageSaveDto;
import com.cos.board.service.BoardService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/saveForm")
	public String saveForm() {
		return "saveForm";
	}
	
	@PostMapping("/save")
	@ResponseBody
	public String save(@RequestBody BoardSaveRequestDto dto,
			@AuthenticationPrincipal PrincipalDetails princiql) {
		boardService.글쓰기(dto,princiql);
		return "ok";
	}
	
	@PostMapping("/imageUpload")
	@ResponseBody
	public ImageSaveDto imageUpload(@RequestParam("file") MultipartFile multipartFile) {
		return boardService.이미지저장(multipartFile);
	}
	
	@DeleteMapping("/board/{id}")
	@ResponseBody
	public String delete(@PathVariable int id) {
		boardService.글삭제하기(id);
		return "ok";
	}
	
	@PutMapping("/board/{id}")
	@ResponseBody
	public String update(@PathVariable int id, @RequestBody BoardSaveRequestDto dto) {
		boardService.글수정하기(id, dto);
		return "ok";
	}
}
