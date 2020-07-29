package com.excilys.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company {
	
	@Id
	public int id; 
	public String name;
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
	
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + " + \n]";
	}

}