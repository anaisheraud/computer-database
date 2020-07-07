package services;

import java.util.List;
import java.util.Scanner;

import com.excilys.beans.Company;
import com.excilys.beans.Computer;
import com.excilys.dao.DaoFactory;

public class ServiceCompany {
	
	 private static DaoFactory daoconnexion = new DaoFactory(); 
	 
	 public static List<Company> lister(){
		 List<Company> allCompany = daoconnexion.getCompanyDao().lister();
		 System.out.println(allCompany);
		 return allCompany;
	 }
	 
	 public static void delete() {
		 
		 Scanner scanner = new Scanner(System.in);
		 System.out.println("Id ?");
		 int id = scanner.nextInt();
		 
		 daoconnexion.getCompanyDao().delete(id);
		 
	 }
	 
}
