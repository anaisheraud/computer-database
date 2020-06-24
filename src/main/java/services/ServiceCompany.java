package services;

import java.util.List;

import com.excilys.beans.Company;
import com.excilys.dao.DaoFactory;

public class ServiceCompany {
	
	 private static DaoFactory daoconnexion = new DaoFactory(); 

	 public static void lister() {
	 
	 List<Company> allCompany = daoconnexion.getCompanyDao().lister();
	 System.out.println(allCompany);
	 
	 }
	 
}
