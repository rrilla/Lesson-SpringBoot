package com.cos.board.config.ex;

public class MyArgsNotFound extends Exception{

	private String message;
	
	public MyArgsNotFound(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
