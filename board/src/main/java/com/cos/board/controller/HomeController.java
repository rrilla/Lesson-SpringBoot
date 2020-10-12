package com.cos.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller	//Spring web라이브러리 다운받아서 사용가능
public class HomeController {
	
	@GetMapping("/home")	//src/main/webapp/WEB-INF/views/home.jsp
	public void test() {
		
	}
	
	
}
