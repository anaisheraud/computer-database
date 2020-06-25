package com.excilys.test;

import com.excilys.beans.Computer;
import com.excilys.dao.CompanyDao;
import com.excilys.dao.CompanyDaoImpl;
import com.excilys.dao.ComputerDao;
import com.excilys.dao.ComputerDaoImpl;
import com.excilys.dao.DaoFactory;

public class testDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	 //DaoFactory permet de faire la connexion
	 DaoFactory objectDao = new DaoFactory();
	 
	 //Pouvoir appeler les méthodes avec cette bdd
	 ComputerDao objectbdd = new ComputerDaoImpl(objectDao);

	 
	 //Computer computer1 = new Computer(579,"Dell", null, null, 1);
	 //objectbdd.ajouter(computer1);
	 
	 Computer computer2 = new Computer(577,"Po", null, null, 1);
	 
	 System.out.println(objectbdd.update(computer2));
	 
	 System.out.println(objectbdd.delete(computer2));
	 
	 System.out.println(objectbdd.lister());
	 
	 
	 CompanyDao objectbdd1 = new CompanyDaoImpl(objectDao);
	 
	 System.out.println(objectbdd1.lister());
	 
	 //System.out.println(objectbdd.lister().get(1).getName());

	}

}
