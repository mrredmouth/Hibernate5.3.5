package com.ccg.hibernate.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity(name="USER_ANNO")
@Data
public class UserAnno {
	@Id
	private Integer id;
	private Name name;
	private String notes;
	private boolean starred;
	
	public UserAnno(){}
	
	public UserAnno(Name name){
		this.name = name;
	}
	
}
