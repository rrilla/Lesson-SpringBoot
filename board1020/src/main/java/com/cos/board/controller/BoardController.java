package com.cos.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.board.config.auth.PrincipalDetails;
import com.cos.board.dto.BoardSaveRequestDto;
import com.cos.board.model.Board;
import com.cos.board.model.User;
import com.cos.board.service.BoardService;
import com.cos.board.service.UserService;

@Controller
@RequestMapping("/all")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/session")
	@ResponseBody
	public String session(Model model,
			@AuthenticationPrincipal PrincipalDetails princiql) {
		try {
			model.addAttribute("user", princiql.getUser().getUsername());
			return "1";
		}catch(NullPointerException e){
			System.out.println("user값 null");
			return "0";
		}
	}
	
	@GetMapping("/security")
	public String security() {
		return "security";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	}
	
	@PostMapping("/joinProc")
	@ResponseBody
	public String joinProc(@RequestBody User user) {
		userService.join(user);
		System.out.println(user.getUsername()+"님이 회원가입하심.");
		return "ok";
	}
	
	@GetMapping({"","/","/list"})	//배열(중괄호)로 다수의 주소 설정가능
	public String list(Model model,
			@AuthenticationPrincipal PrincipalDetails princiql,
			@PageableDefault(size = 5, sort = "id", direction = Direction.DESC ) Pageable pageable) {	//sort=정렬을 뭐로해서 페이징할지(모델의 pk, db의 칼럼명)
		try {
			model.addAttribute("user", princiql.getUser().getUsername());
		}catch(NullPointerException e){
			System.out.println("user값 null");
		}
		
		model.addAttribute("boards", boardService.글목록(pageable));
		model.addAttribute("test", boardService.글목록(pageable).getContent());
		return "list";
	}
	
	//page테스트용
	@GetMapping({"/list/test"})
	@ResponseBody
	public Page<Board> listTest(
			@PageableDefault(size = 5, sort = "id", direction = Direction.DESC ) Pageable pageable) {	//sort=정렬을 뭐로해서 페이징할지(모델의 pk, db의 칼럼명)
		return boardService.글목록(pageable);
	}
	
	//검색 - 페이징, db쿼리 칼럼명 변수처리 미해결
	@GetMapping("/search")
	public String search(Model model, String search, String type,
			@PageableDefault(size = 5, sort = "id", direction = Direction.DESC ) Pageable pageable) {
		model.addAttribute("test", boardService.글검색(search, pageable));
		return "list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable int id, Model model) throws Exception {
		model.addAttribute("board", boardService.글상세보기(id));
		return "detail";
	}
	
}
