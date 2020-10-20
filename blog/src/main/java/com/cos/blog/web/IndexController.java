package com.cos.blog.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@GetMapping({"", "/"})
	public String index() {
		return "index";
	}
	
	@GetMapping("/manager")
	public String manager() {
		return "manager";
	}
	
	@GetMapping("/admin")
	public String admins() {
		return "admin";
	}
	
	@GetMapping("/loginForm")
	public String loginForm(){
		return "loginForm";
	}
	
}
