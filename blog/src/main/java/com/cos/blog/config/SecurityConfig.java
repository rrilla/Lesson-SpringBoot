package com.cos.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration	//메모리에 띄움
@EnableWebSecurity		//시큐리티 설정파일이 활성화됨.
public class SecurityConfig extends WebSecurityConfigurerAdapter{	//어댑터가있다? 그럼 인터페이스의 원형이 있겠찌?
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();     //postman이나 fetch요청 불가능하다 요거 안하모
		http.authorizeRequests()
		.antMatchers("/manager/**", "/admin/**")	//manager, admins 머시기는 인증이필요함 
		.authenticated()
		.anyRequest()
		.permitAll();
		//.and()
		//.formLogin()
		//.loginPage("/loginForm")	//인증 필요한 사람 오면 이페이지로 보내버림
		//.loginProcessingUrl("loginProc");//loginForm 의 action 주소설정
	}
}
