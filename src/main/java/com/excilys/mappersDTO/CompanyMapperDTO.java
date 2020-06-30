package com.excilys.mappersDTO;

import com.excilys.beans.Company;
import com.excilys.dto.CompanyDTO;

public class CompanyMapperDTO {

	public static Company CompanyDtoToCompany(CompanyDTO companyDto) {
		Company company = new Company(companyDto.getId(),companyDto.getName());
		return company;
	}
	
	public static CompanyDTO companytoCompanyDto(Company company) {
		CompanyDTO companyDto = new CompanyDTO();
		companyDto.setId(company.getId());
		companyDto.setName(company.getName());
		return companyDto;
	}
	
}
