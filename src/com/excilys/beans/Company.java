/*
 * 
 * Fichier assez simple qui contient des gets et sets
 */

package com.excilys.beans;

public class Company {
	
	private int id; 
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Company(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Company() {
		
	}

}

