package com.cos.blog.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cos.blog.test.intercepter.MyIntercepter;

//@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(new MyIntercepter())
		.addPathPatterns("/test/admin");		//		"/test/admin"요청이들오면 얘를 작동시킴
		//.addPathPatterns("url")	//또실행하고싶은 놈 요렇게 추가가능
		//.excludePathPatterns("/test/admin/a")	// 입력한 url은 제외하고 실행됨. 
		//		/test/admin/** 적용시 admin 이하 다 작동.
	}
}
