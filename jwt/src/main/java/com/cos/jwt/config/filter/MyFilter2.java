package com.cos.jwt.config.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class MyFilter2 implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("필터2실행");
		
		HttpServletResponse res = (HttpServletResponse) response;
		res.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
		//res.setHeader("Access-Control-Allow-Origin", "*");
		
		chain.doFilter(request, response);		//다음 필터를 타라. (MyFilter2)
	}

}
