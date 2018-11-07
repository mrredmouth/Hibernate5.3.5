package com.ccg.hibernate.entities;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class Name {
	private String first;
	private String middle;
	private String last;

	public Name(){}
	public Name(String first){
		this.first = first;
	}
}
