package com.excilys.services;

import java.util.List;
import java.util.Scanner;

import com.excilys.beans.Company;
import com.excilys.beans.Computer;
import com.excilys.dao.CompanyDaoImpl;
import com.excilys.dao.DaoFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceCompany {
	
	 @Autowired
	 private CompanyDaoImpl CompanyDao;
	 
	 /**
	  * @return ListCompanies
	  */
	 public List<Company> lister(){
		 List<Company> allCompany = CompanyDao.lister();
		 System.out.println(allCompany);
		 return allCompany;
	 }
	 
	 /**
	  * Suprimer la compagnie
	  * @param id
	  */
	 public void delete(int id) {	 
		 CompanyDao.delete(id); 
	 }
	 
}
