package com.example.demo.model;

public class Post {
	private int id;
	private String title;
	private String content;
	private int readCount;
	
	public Post(int id, String title, String content, int readCount) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.readCount = readCount;
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
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
}

