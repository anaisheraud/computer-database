package com.excilys.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.beans.Computer;
import com.excilys.dao.ComputerDaoImpl;

@Service
public class ServiceComputer {
	
	 @Autowired
	 private ComputerDaoImpl ComputerDao;
	
	 //spring :
	 //private DaoFactory daoconnexion = new DaoFactory(); 
	 
	 /* 
	  * Création des méthodes computer 
	  * 
	  * */
	 
	 public List<Computer> listerpage(int entier1, int entier2, int lenPage) {
		 
		 List<Computer> allComputer = ComputerDao.listerpage(entier1, entier2, lenPage);
		 return allComputer;
		 
	 }
	 
	 public List<Computer> lister() {
		 
		 List<Computer> allComputer = ComputerDao.lister();
		 return allComputer;
		 
	 }
	 
	 public Computer showdetails(int id) { 
		 
		 return ComputerDao.lister().get(id-1);
		 
	 }
	 
	 public void create(Computer computer) {
				
		 ComputerDao.ajouter(computer);
		 
		 System.out.println("It's ok ! Computer is created ");
		 
	 }
	 
	 public void update(Computer computer) {
		 		 
		 ComputerDao.update(computer);
		 
	 }
	 
	 public void delete(Computer computer) {
		 
		 computer.setId(computer.getId());
		 
		 ComputerDao.delete(computer);
		 
		 System.out.println("It's ok ! Computer is delete ");
		 
	 }
	 
	 public int getAll() {
		 
		 return ComputerDao.getAll();
		 
	 }
	 
	 public Computer find(int id) {
		 
		 return ComputerDao.find(id);
		 
	 }
	 
	 public List<Computer> getByName(String search) {
		 
		 return ComputerDao.getbyName(search);
		 
	 }
	 
	 public List<Computer> orderBy() {
		 
		 return ComputerDao.orderBy();
		 
	 }
	 
}
