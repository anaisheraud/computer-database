package com.excilys.dto;
/*
 * 
 * Fichier assez simple qui contient des gets et sets
 */

public class ComputerDTO {
	
	private String id; 
	private String name;
	//forcer localdate
	private String introduced; //date 
	private String discontinued;
	private String company_id;
	
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
	public String getIntroduced() {
		return introduced;
	}
	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}
	public String getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	
	public ComputerDTO(String name, String introduced, String discontinued, String company_id) {
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company_id = company_id;
	}
	
	public ComputerDTO() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", company_id=" + company_id + " + \n]";
	}

}

