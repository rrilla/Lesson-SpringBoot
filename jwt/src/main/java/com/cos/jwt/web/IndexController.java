package com.cos.jwt.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cos.jwt.domain.person.Person;
import com.cos.jwt.domain.person.PersonRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class IndexController {

	private final PersonRepository personRepository;
	
	@GetMapping({"/",""})
	public String index() {
		return "index";
	}
	
	@PostMapping("/joinProc")
	public  String joinProc(@RequestBody Person person) {
		personRepository.save(person);
		return "ok";
	}
	
	
	
	//@CrossOrigin
	//@CrossOrigin(origins = "http://127.0.0.1:5500", methods = RequestMethod.GET)
	//origins : 해당 url만 허용, methods : 해당 메서드만 허용
	//response할 때 도메인달라도 js요청 허용해줄게//메서드,클래스 다사용가능
	@GetMapping("person/{id}")
	public ResponseEntity<?> info(@PathVariable int id, 
			HttpServletResponse response,
			HttpServletRequest request) {
		//response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
		String jwtToken = request.getHeader("Authorization");
		System.out.println(jwtToken);
		
		
		if(jwtToken == null ) {
			return new ResponseEntity<String>("니인증안됨",HttpStatus.FORBIDDEN);	//403이 던져질거임-인증안된
		}else {
			jwtToken = jwtToken.replace("Bearer", "");
			
			DecodedJWT decode = JWT.require(Algorithm.HMAC512("메돌이")).build().verify(jwtToken);
			
			int personId = decode.getClaim("id").asInt();	//jwt토큰에서 id값을 디코딩하여 얻기
			
			if(personId != 0) {
				System.out.println("personId : " + personId);
				System.out.println(personId);
			}
			
			return new ResponseEntity<Person>(personRepository.findById(id).get(),HttpStatus.OK);
		}
	}

}
