package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PostRequestDto;
import com.example.demo.model.Post;
import com.example.demo.model.PostRepository;

@RestController
public class HomeController {

	PostRepository postRepository = new PostRepository();
	
	@GetMapping("/post/{id}")
	public Post dataOne(@PathVariable int id) {
		System.out.println("id가 '"+id+"'인 게시글 조회");
		return postRepository.게시글(id);
	}
	
	@GetMapping("/post")
	public List<Post> dataList() {
		System.out.println("게시글 목록 전체 조회");
		return postRepository.게시글목록();
	}
	
	@PostMapping("/post")
	public int save(@RequestBody PostRequestDto postRequestDto) {
		System.out.println("title : " +postRequestDto.getTitle());
		System.out.println("content : " +postRequestDto.getContent());
		System.out.println("로 데이터 저장");
		return postRepository.게시글저장(postRequestDto);
	}
	
	@PutMapping("/post/{id}")
	public int update(@RequestBody PostRequestDto postRequestDto, @PathVariable int id) {
		System.out.println("id가 '" + id+"'인 게시글 수정");
		System.out.println(postRequestDto.getTitle());
		System.out.println(postRequestDto.getContent());
		return postRepository.게시글수정(postRequestDto);
	}
	
	@DeleteMapping("/post/{id}")
	public int delete(@PathVariable int id) {
		System.out.println("id가 '"+id+"'인 게시글 삭제");
		return postRepository.게시글삭제(id);
	}
	
}
