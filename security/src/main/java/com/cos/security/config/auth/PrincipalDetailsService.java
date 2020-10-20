package com.cos.security.config.auth;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.security.domain.user.User;
import com.cos.security.domain.user.UserRepository;

//princpal : 접근 주체(인증 주체, 주로 세션값으로 사용)
@Service	//ioc등록
public class PrincipalDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	//로그인 proc호출되면 username, password를 들고 일로옴. 근데 기존 리턴값이 null이라 동작 없었던거임!
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("로그인 요청됨!!!");
		
		System.out.println(username);
		
		User user = userRepository.findByUsername(username);
		
		if(user == null) {
			return null;
		}else {
			return new PrincipalDetails(user);
//			return new UserDetails() {
//				//스프링이 기본적으로 쓰는 유저디테일 정보
//				
//				@Override
//				public boolean isEnabled() {	//니아디 활성화인지 비활성화인지, 일정기간 사용x시
//					// TODO Auto-generated method stub
//					return true;
//				}
//				
//				@Override
//				public boolean isCredentialsNonExpired() {
//					//비밀번호 
//					// TODO Auto-generated method stub
//					return true;	//true=신경안쓰겠다
//				}
//				
//				@Override
//				public boolean isAccountNonLocked() {
//					//비번 5번이상 틀리면 우쨰할지
//					// TODO Auto-generated method stub
//					return false;
//				}
//				
//				@Override
//				public boolean isAccountNonExpired() {
//					// TODO Auto-generated method stub
//					return false;
//				}
//				
//				@Override
//				public String getUsername() {
//					// TODO Auto-generated method stub
//					return user.getUsername();
//				}
//				
//				@Override
//				public String getPassword() {
//					// TODO Auto-generated method stub
//					return user.getPassword();
//				}
//				
//				@Override
//				public Collection<? extends GrantedAuthority> getAuthorities() {
//					// TODO Auto-generated method stub
//					return null;
//				}
//			};//패스워드//패스워드 검증
		}
	}

}
