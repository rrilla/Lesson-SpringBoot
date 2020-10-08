package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;

@RestController	//데이터 리턴,, 뷰 리졸버가 발동안함
public class HomeController {

	@GetMapping("/home")	//컨텐츠 타입 = plain/text
	public String home() {
		return "Home";
	}
	
	//http://localhost:8000/user
	//@GetMapping("/user")	//컨텐츠 타입 = application/json
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		users.add(new User(1,"jaehyeon","부산"));
		users.add(new User(1,"suzin","창원"));
		users.add(new User(1,"okja","함안"));
		return users;	//자바 컬렉션 -> json으로 변경해서 리턴해줌.
		//종료시에 리턴타입이 object라면 무조건 json으로 리턴 - 기본설정임
		//MessageConverter(jackson라이브러리연결되잇음)라는 추상클래스에서 실행
	}
	
	//http://localhost:8000/user/1
	@GetMapping("/user")
	public User findById( int id) {
		User user = new User(1,"hihi","부산");
		System.out.println(id);
		return user;
	}
							//?username=jaehyeon&addr=haman
	@PostMapping("/user")//x-www-form-urlencoded를 받음(form 태그의 양식)
	public User save(@RequestBody User user) {	
		//@RequestBody는 MessageConverter를 작동시킴 so json데이터를 받을 수 있음(json파싱).
		System.out.println("username : " + user.getUsername());
		System.out.println("addr : " + user.getAddr());
		return null;
	}
	
//	@PostMapping("/user")
//	public User save(User user) {
//		System.out.println(user.getUsername());
//		System.out.println(user.getAddr());
//		return null;
//	}
}
