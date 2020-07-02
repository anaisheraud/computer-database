/* Fichier assez simple qui contient des gets et sets */

package com.excilys.dto;

public class CompanyDTO {
	
	private String id; 
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
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