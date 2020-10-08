package com.example.demo.dto;

public class UserRequestDto {
private String username;
private String addr;


public UserRequestDto(String username, String addr) {
	this.username = username;
	this.addr = addr;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getAddr() {
	return addr;
}
public void setAddr(String addr) {
	this.addr = addr;
}


}
