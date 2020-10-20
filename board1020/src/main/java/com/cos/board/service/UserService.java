package com.cos.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cos.board.model.User;
import com.cos.board.repository.UserReposity;

@Service
public class UserService {

	@Autowired
	private UserReposity userReposity;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void join(User user) {
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole("ROLE_USER");
		userReposity.save(user);
	}
	
}
