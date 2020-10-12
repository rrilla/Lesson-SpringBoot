package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.PostRequestDto;

public class PostRepository {
	
	public static List<Post> 게시글목록() {
		List<Post> posts = new ArrayList<>();
		posts.add(new Post(1,"제목1","내용1",0));
		posts.add(new Post(2,"제목2","내용2",0));
		posts.add(new Post(3,"제목3","내용3",0));
		
		return posts;
	}
	
	public static Post 게시글(int id) {
		List<Post> posts = new ArrayList<>();
		posts.add(new Post(1,"제목1","내용1",0));
		posts.add(new Post(2,"제목2","내용2",0));
		posts.add(new Post(3,"제목3","내용3",0));
		posts.add(new Post(4,"제목4","내용4",0));
		posts.add(new Post(5,"제목5","내용5",0));
		
		if(id == 1) {
			return posts.get(0);
		}else if(id==2) {
			return posts.get(1);
		}else if(id==3) {
			return posts.get(2);
		}else {
			return null;
		}
	}
	
	public int 게시글저장(PostRequestDto postRequestDto) {
		return 1;
	}
	
	public int 게시글수정(PostRequestDto postRequestDto) {
		return 1;
	}
	
	public int 게시글삭제(int id) {
		return 1;
	}


}
