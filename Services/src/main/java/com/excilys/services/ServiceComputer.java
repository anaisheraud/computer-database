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
	
	 /**
	  * Liste des ordinateurs par page
	  * @param entier1
	  * @param entier2
	  * @param lenPage
	  * @return ListComputers
	  */
	 public List<Computer> listerpage(int entier1, int entier2, int lenPage) { 
		 List<Computer> allComputer = ComputerDao.listerpage(entier1, entier2, lenPage);
		 return allComputer;	 
	 }
	 
	 /**
	  * @return ListComputers
	  */
	 public List<Computer> lister() {	 
		 List<Computer> allComputer = ComputerDao.lister();
		 return allComputer; 
	 }
	 
	 /**
	  * @param id
	  * @return computer
	  */
	 public Computer showdetails(int id) {  
		 return ComputerDao.lister().get(id-1); 
	 }
	 
	 /**
	  * Crée un ordinateur
	  * @param computer
	  */
	 public void create(Computer computer) {		
		 ComputerDao.ajouter(computer);	 
	 }
	 
	 /**
	  * Modifie un ordinateur
	  * @param computer
	  */
	 public void update(Computer computer) { 		 
		 ComputerDao.update(computer); 
	 }
	 
	 /**
	  * Supprime un ordinateur
	  * @param computer
	  */
	 public void delete(Computer computer) {
		 computer.setId(computer.getId()); 
		 ComputerDao.delete(computer);
	 }
	 
	 /** 
	  * @return le nombre d'ordinateurs de la bdd
	  */
	 public int getAll() {
		 return ComputerDao.getAll();
	 }
	 
	 /**
	  * @param id
	  * @return un ordinateur dans la bdd
	  */
	 public Computer find(int id) {
		 return ComputerDao.find(id);
	 }
	 
	 /**
	  * @param search
	  * @return un ordinateur avec son nom dans la bdd
	  */
	 public List<Computer> getByName(String search) { 
		 return ComputerDao.getbyName(search); 
	 }
	 
	 /**
	  * @return ListComputers ordonnés
	  */
	 public List<Computer> orderBy() { 
		 return ComputerDao.orderBy();	 
	 }
	 
}
