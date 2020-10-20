package com.cos.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//시큐리티의 기본설정에 오버라이딩, 커스터마이징(내가설정바꿈)하고싶다. 
@EnableWebSecurity	//시큐리티 설정파일 활성화
@Configuration	//	IOC등록, 메모리에 띄움
public class SecurityConfig extends WebSecurityConfigurerAdapter{	
	//요 기능은 시큐리티 라이브러리, 스프링에서 설정해준거라 내가 직접 못바꿈, 대신 지들이 다른걸 상속받는 식으로 구현 해놓음 ,
	//사용하기 위해서 상속받아서 타입을 똑같이 맞추고, IOC등록, 
	
	@Bean	//메서드의 리턴값을 IOC에 등록
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	//요래하모 salt값이 계쏙달라진다 싱글톤으로 관리해야됨 >> IOC
	
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
		.loginPage("/loginForm")	//인증 필요한 사람 오면 이페이지로 보내버림
		.loginProcessingUrl("/loginProc")//loginForm 의 action 주소설정???
		//매개변수주소요청이오면 시큐리티 필터가 가로채서 대신 로그인 진행
		.defaultSuccessUrl("/")	//로그인 성공했을시 이동할 페이지. 내가 설정한 경로에서 쫓겨난놈은 로그인성공시 다시그페이지
		//걍 loginForm으로 요청한사람은 /루트 경로로 이동. 설정 안할 시 반복됨..
		.and()
		.logout()
		.logoutSuccessUrl("/logoutProc");
	}
}
