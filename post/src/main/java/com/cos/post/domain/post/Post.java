package com.cos.post.domain.post;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cos.post.domain.user.User;

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
public class Post {
	@Id	//기본키 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//해당 데이터베이스 번호증가 전략을 따라가기
	private int id;
	private String title;
	@Column(length = 100000)
	private String content;
	private int readCount;
	
	//User에서 Posts를 호출 할 때만 무시
	@JoinColumn(name="userId")	//요거없이 테이블생성시 user_id로되는데 난 카멜표기법이좋다! 바꿔버리자!
	@ManyToOne(fetch = FetchType.EAGER)	//테이블 관계, fk적용하여 db에 칼럼 생성
	private User user;	//연관관계를 위한 칼럼 - FK,
	//insert할 때 user의 id값만 넣어주고, select할 때 id값을 이용해서 select하여 오브젝트를 넣어줌.
}
