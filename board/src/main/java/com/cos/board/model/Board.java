package com.cos.board.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data -> Getter,Setter 합쳐져 있음. + toString()
//@Getter
//@Setter
@AllArgsConstructor	//전체 파라메타를 가진 생성자
@NoArgsConstructor	//default 생성자(파라메터x)
@Builder	//빌더 패턴
@Data
@Entity	//ORM가능, object(board)를 가지고 db
public class Board {
	@Id	//기본키 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//해당 데이터베이스 번호증가 전략을 따라가기
	private int id;
	private String title;
	private String content;
	private int readCount;
	@CreationTimestamp	//default 현재시간 자동 적용
	private Timestamp createDate;
	
	public String getCreateDate() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		//SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
		//String time[] = {sdf.format(createDate), sdf2.format(createDate)};
		
		return sdf.format(createDate);
	}
	
	
//	public Timestamp getCreateDate() {
//		//return "2020-11-11";
//		return Timestamp.valueOf(createDate.toString().substring(0,10));
//	}
}
