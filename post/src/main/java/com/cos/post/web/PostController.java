package com.cos.post.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.post.domain.post.Post;
import com.cos.post.domain.post.PostRepository;
import com.cos.post.domain.user.User;
import com.cos.post.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostController {

	private final PostRepository postRepository;
	private final UserRepository userRepository;
	
	@PostMapping("/post")
	public String write(@RequestBody Post post) {
		
		User userEntity = userRepository.findById(1).get();
		
		post.setReadCount(0);
		post.setUser(userEntity);
		postRepository.save(post);
		return "ok";
	}
	
	@GetMapping("/post")
	public List<Post> list(){
		return postRepository.findAll();
	}
	
	@GetMapping("/post/{id}")
	public Post detail(@PathVariable int id) {
		Post post = postRepository.findById(id).get();
		return post; // Jackson 라이브러리 - gettter실행
	}
}
