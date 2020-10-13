package com.cos.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller	//Spring web라이브러리 다운받아서 사용가능
public class HomeController {
	
	@GetMapping("/home")	//src/main/webapp/WEB-INF/views/home.jsp
	public void test() {
		
	}

	
	
	@PostMapping("/test")
	@ResponseBody
	public String test2(@RequestBody String t, String content) {
		System.out.println(t);
		System.out.println(content);
		
		return t;
	}
	
	
}
