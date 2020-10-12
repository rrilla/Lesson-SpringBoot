package com.cos.board.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class BoardTest {
	
	@Test	//함수별 테스트를 할수있음
	public void lombok_test() {
		System.out.println("=====================");
		
		//1.Board 모델
		Board board1 = new Board();
		board1.setId(1);
		board1.setTitle("제목1");
		System.out.println(board1);
		
		Board board2 = new Board(
				2,
				"제목2",
				"content2",
				0,
				Timestamp.valueOf(LocalDateTime.now())
				);
		System.out.println(board2);
		
		Board board3 = Board.builder().title("제목3").content("내용3").build();
		System.out.println(board3);
		
		//id추가 하기싫어도 해야함 ㅡㅡ
		//생성자에 매개변수 순서가 헷갈린다.
		//그래서 빌더패턴을 쓰자!!
		
		System.out.println("=====================");
	}
	
	@Test
	public void 시간테스트() {
		Date date = new Date();
		System.out.println(date);
		
		Time time = new Time(10);
		System.out.println(time);
		
		String date2 = LocalDate.now().toString();
		System.out.println(time);
		
		String time2 = LocalDateTime.now().toString();
		System.out.println(time);
		
		//시간을 타임 스탬프로 변환
		Timestamp ts = Timestamp.valueOf(LocalDateTime.now());
		System.out.println(ts);
	}
}
