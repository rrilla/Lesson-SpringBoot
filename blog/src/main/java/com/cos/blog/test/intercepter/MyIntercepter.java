package com.cos.blog.test.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

// @Component
public class MyIntercepter implements HandlerInterceptor{

	//특정 컨트롤러의 함수 요청시에 session 값 확인
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("MyIntercepter preHandle() 실행됨");
		return true;	//true이면 함수 내부 실행
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("MyIntercepter postHandle() 실행됨");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
}
