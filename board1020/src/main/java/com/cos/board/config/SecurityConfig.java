package com.cos.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean	//IOC등록하여 싱글톤으로 관리, 안하모 salt값이 계속바뀜...
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();	//	form태그 요청만 가능한 것을 비활성화 함.postman이나 fetch요청 불가능하다 요거 안하모
		
		http.authorizeRequests()
		.antMatchers("/user/**").authenticated()	//매개변수안 주소는 세션이(인증이) 필요함
		.antMatchers("/manager/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
		.anyRequest().permitAll()	//다른 모든 요청은 필요없음!
		.and()
		.formLogin()
		.loginPage("/all/security")	//인증 필요한 사람 오면 이페이지로 보내버림
		.loginProcessingUrl("/all/loginProc")//loginForm 의 action 주소설정???
		.defaultSuccessUrl("/all/list") //그냥 loginForm에서 요청했을 때 어느 페이지로 갈지 
		.and()
		.logout()
		.logoutSuccessUrl("/all/list");	//여기서 request어케사용, 이전페이지 가는법
	}
}
