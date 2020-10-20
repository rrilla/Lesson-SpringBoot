package com.cos.post.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.post.domain.user.User;
import com.cos.post.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserController {

	private final UserRepository userRepository;
	
	@PostMapping("/user")
	public String join(@RequestBody User user) {
		userRepository.save(user);
		return "ok";
	}
	
	@GetMapping("/user")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	@GetMapping("/user/{id}")
	public User detail(@PathVariable int id){
		User user = userRepository.findById(id).get();
		return user; // Jackson 라이브러리 - gettter실행
	}
}
