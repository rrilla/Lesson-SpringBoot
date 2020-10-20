package com.cos.blog.test.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cos.blog.test.filter.MyFilter1;
import com.cos.blog.test.filter.MyFilter2;

//@Configuration	//ioc등록하기 위한용도, 
public class FilterConfig {

	//원래 클래스가 confi때매 생성되면 힙메모리에 함수의 이름만 넣어놓는데
	//bean을 붙여서 강제로 리턴값을 ioc에 등록
	@Bean	//함수의 리턴타입을 IOC 등록
	public FilterRegistrationBean<MyFilter1> myFilter1(){
		System.out.println("서버 돌모 내가 도나?");
		FilterRegistrationBean<MyFilter1> bean = 
				new FilterRegistrationBean<>(new MyFilter1());
		//MyFilter가 스프링 필터에 등록됨
		bean.addUrlPatterns("/*");	//모든 요청에
		bean.setOrder(0);	//낮은 번호부터 실행됨.
		return bean;		//만들어진 bean을 IOC로 등록하기위하여 리턴
	}
	
	@Bean
	public FilterRegistrationBean<MyFilter2> myFilter2(){
		FilterRegistrationBean<MyFilter2> bean = 
				new FilterRegistrationBean<>(new MyFilter2());
		//MyFilter가 스프링 필터에 등록됨
		bean.addUrlPatterns("/*");	//모든 요청에
		bean.setOrder(0);	//낮은 번호부터 실행됨.
		return bean;		//만들어진 bean을 IOC로 등록하기위하여 리턴
	}
	
}
