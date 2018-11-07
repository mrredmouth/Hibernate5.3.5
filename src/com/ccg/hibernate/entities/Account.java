package com.ccg.hibernate.entities;

import java.io.Serializable;

import lombok.Data;

@Data
public class Account implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String username;
	private int balance;
}
