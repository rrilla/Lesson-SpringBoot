package com.cos.jwt.config.jwt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cos.jwt.domain.person.Person;
import com.cos.jwt.domain.person.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


public class JwtAuthenticationFilter implements Filter{

	private PersonRepository personRepository;
	
	public JwtAuthenticationFilter(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();	//http body로 뿌릴꺼임
		
		String method = req.getMethod();
		if(!method.equals("POST")) {
			System.out.println("잘못된 요청, post만 가능");
			out.print("post로 요청하셈.");
			out.flush();
			return;
		}else {
			System.out.println("post요청이 맞음!");
			
			ObjectMapper om = new ObjectMapper();
			try {
				Person person = om.readValue(req.getInputStream(), Person.class);
				System.out.println(person);
				String username = person.getUsername();
				String password = person.getPassword();
				//Person dbPerson = personRepository.findByUsernameAndPassword(username, password);
				Person dbPerson = personRepository.check(username, password);
				System.out.println(dbPerson);
				
				if(dbPerson != null) {
					System.out.println("아디비번일치함.");
					//String jwtHeader = "{\"alg\" : \"HS256\"}";
					//String jwtMsg = "{\"userId\" : "+dbPerson.getId()+"}";
					
					String jwtToken = 
							JWT.create().withSubject("토큰의제목-코스토큰")	//builder패턴
							.withExpiresAt(new Date(System.currentTimeMillis()+1000*60*30))	//토큰유효기간 30분
							.withClaim("id", dbPerson.getId())//with 비공개 클레임 ,넣고싶은거 다되는데 개인정보,중요한거 ㄴ
							.withClaim("username", dbPerson.getUsername())
							.sign(Algorithm.HMAC512("메돌이"));	//노출되면안됨.
					
					res.addHeader("Authorization", "Bearer" + jwtToken);
					out.print("ok");
					out.flush();
				}else {
					System.out.println("아디비번틀림.");
					out.print("no");
					out.flush();
				}
				//req.setAttribute("person", person);
				
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			
			/*StringBuilder data = new StringBuilder();
			BufferedReader br = req.getReader();
			String input = "";
			while((input = br.readLine()) != null) {
				data.append(input);
			}
			System.out.println(data.toString());
			
			Gson gson = new Gson();
			Person person = gson.fromJson(data.toString(), Person.class);
			System.out.println(person);*/
		}
		System.out.println("JwtAuthenticationFilter 동작");
	}

}
