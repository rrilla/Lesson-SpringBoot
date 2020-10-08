package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//1 getter,setter
//2 빈생성자,풀생성자
@Entity
public class Post {
	@Id	//id로 지정
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//자동증가 전략- db마다 다 다른데 그거에 맞는걸 설정해줌.IDENTITY는 각 db에 맞는 정책을 따라감 
	private int id;
	private String title;
	private String content;
	private int readCount;
	
	public Post() {
	}
	public Post(int id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
