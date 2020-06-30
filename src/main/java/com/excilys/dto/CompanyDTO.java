/* Fichier assez simple qui contient des gets et sets */

package com.excilys.dto;

public class CompanyDTO {
	
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
	
	public CompanyDTO(String name) {
		this.name = name;
	}
	
	public CompanyDTO() {
		
	}
	
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + " + \n]";
	}

}