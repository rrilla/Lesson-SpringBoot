package com.cos.board.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.board.model.User;
import com.cos.board.repository.UserReposity;

@Service
public class PrincipalDetailsService implements UserDetailsService{

	@Autowired
	private UserReposity userReposity;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("로그인호출됨."+username);
		
		User user = userReposity.findByUsername(username);
		if(user == null) {
			
			return null;
		}else {
			return new PrincipalDetails(user);
		}
	}

}
