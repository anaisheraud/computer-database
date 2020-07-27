package com.excilys.mappersDTO;

import com.excilys.beans.Company;
import com.excilys.dto.CompanyDTO;

/**
 * Convertir le résultat de la bdd pour chaque ordis en objet
 */
public class CompanyMapperDTO {

	public static Company CompanyDtoToCompany(CompanyDTO companyDto) {
		Company company = new Company((int)Integer.valueOf(companyDto.getId()),companyDto.getName());
		return company;
	}
	
	public static CompanyDTO companytoCompanyDto(Company company) {
		CompanyDTO companyDto = new CompanyDTO();
		companyDto.setId(String.valueOf(company.getId()));
		companyDto.setName(company.getName());
		return companyDto;
	}
	
}
