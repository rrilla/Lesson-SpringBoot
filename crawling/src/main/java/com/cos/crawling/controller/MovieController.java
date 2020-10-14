package com.cos.crawling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.crawling.service.CrawlingService;

@Controller
public class MovieController {

	@Autowired
	CrawlingService crawlingService;
	
	@GetMapping("movie")
	public String list(Model model) {
		model.addAttribute("movies",crawlingService.영화목록());
		return "list";
	}
	
}
