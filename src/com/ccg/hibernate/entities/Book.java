package com.ccg.hibernate.entities;

import java.io.Serializable;

import lombok.Data;

@Data
public class Book implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String bookName;
	private String isbn;
	private int price;
	private int stock;
	
	private BookType bookType;
}
