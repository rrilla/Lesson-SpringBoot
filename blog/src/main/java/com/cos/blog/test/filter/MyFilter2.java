package com.cos.blog.test.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter2 implements Filter{

	public static final String TAG = "MyFilter2 : ";
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println(TAG + "doFilter() 탔음!");
		chain.doFilter(request, response);		//다음 필터를 타라. (MyFilter2)
		
//		HttpServletRequest req = (HttpServletRequest) request;
//		if(req.getParameter("name") == null) {
//			HttpServletResponse res = (HttpServletResponse) response;
//			res.setContentType("text/html;charset=utf-8");
//			PrintWriter out = res.getWriter();
//			out.print("<script>alert('니 이름없다 넣어라')</script>");
//			out.flush();
//			System.out.println("니 이름없다 넣어라");
//		}else {
//			chain.doFilter(request, response);		//다음 필터를 타라. (MyFilter2)
//		}
		
	}

}
