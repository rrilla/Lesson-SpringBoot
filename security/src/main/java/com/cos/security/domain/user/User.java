package com.cos.security.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//Builder패턴 사용시 전체매개변수필요한 생성자가 자동생성,그래서 디폴트 생성자안생김ㅠ 명시적으로 생성해줘야함.
public class User {

	@Id	//기본키 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//해당 데이터베이스 번호증가 전략을 따라가기
	private int id;
	
	@Column(unique = true)	//db의 unique설정 on
	private String username;
	private String password;
	private String email;
	private String role;	//권한 : 
	
}
