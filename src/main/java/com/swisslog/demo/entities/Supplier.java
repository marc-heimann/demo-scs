package com.swisslog.demo.entities;

import java.io.Serializable;

public class Supplier implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3770483989040140055L;
	
	long id;
	String name;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
