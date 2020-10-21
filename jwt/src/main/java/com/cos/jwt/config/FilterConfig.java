package com.cos.jwt.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cos.jwt.config.filter.CorsFilter;
import com.cos.jwt.config.jwt.JwtAuthenticationFilter;
import com.cos.jwt.domain.person.PersonRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class FilterConfig {

	public final PersonRepository personRepository;
	
	@Bean
	public FilterRegistrationBean<CorsFilter> myFilter1(){
		System.out.println("난 필터다. 서버돌면 돈다.1");
		FilterRegistrationBean<CorsFilter> bean = 
				new FilterRegistrationBean<>(new CorsFilter());
		//MyFilter가 스프링 필터에 등록됨
		bean.addUrlPatterns("/api/*");	//주소설계할 때 요청하는 주소를 api로 잡아버리고 api가 요청할 때 요렇게 설정해서 cors필터적용해버리면될듯
		bean.addUrlPatterns("/*");	//모든 요청에
		bean.setOrder(0);	//낮은 번호부터 실행됨.
		return bean;		//만들어진 bean을 IOC로 등록하기위하여 리턴
	}
	
	@Bean
	public FilterRegistrationBean<JwtAuthenticationFilter> JwtAuthenticationFilter(){
		System.out.println("난 필터다. 서버돌면 돈다.2");
		FilterRegistrationBean<JwtAuthenticationFilter> bean = 
				new FilterRegistrationBean<>(new JwtAuthenticationFilter(personRepository));
		//MyFilter가 스프링 필터에 등록됨
		bean.addUrlPatterns("/loginProc");	//모든 요청에
		bean.setOrder(1);	//낮은 번호부터 실행됨.
		return bean;		//만들어진 bean을 IOC로 등록하기위하여 리턴
	}
}
