package com.ccg.hibernate.entities;

import lombok.Data;

@Data
public class User {

	private Integer userCode;
	private String userName;
	private String password;
	private String email;
	private Long phoneNumber;
	
	public User(){}
	
	public User(String userName,String password,String email,long phoneNumber){
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	public User(Integer userCode,String userName,String password,String email,long phoneNumber){
		this.userCode = userCode;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
}
