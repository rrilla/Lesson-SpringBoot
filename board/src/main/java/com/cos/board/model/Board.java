package com.cos.board.model;

import java.sql.Timestamp;

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
public class Board {
	private int id;
	private String title;
	private String content;
	private int readCoount;
	private Timestamp createDate;
}
