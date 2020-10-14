package com.cos.board.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class UploadFile {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)	//해당 데이터베이스 번호증가 전략을 따라가기
	    private int id;
	    private String fileName;
	    private String saveFileName;
	    private String filePath;
	    private String contentType;
	    private long size;
	    private Date regDate;
}
