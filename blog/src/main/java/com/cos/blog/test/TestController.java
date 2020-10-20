package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@GetMapping("/test")
	public String home() {
		return "<h1>home</h1>";
	}
	
	@GetMapping("/test/admin")
	public String admins() {
		return "<h1>admin</h1>";
	}

}
