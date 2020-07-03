/*
 * On définit les méthodes ici sans les implémenter
 * c'est l'intermédiaire de Dao
 * Et on définira les méthodes dans notre implémentation ComputerDaoImpl
 */
package com.excilys.dao;

import java.util.List;

import com.excilys.beans.Computer;

public interface ComputerDao {
	//Ajoute de l'ordinateur et lister les suivants
	List<Computer> listerpage(int entier1, int entier2, int lenPage);
	List<Computer> lister();
	void ajouter(Computer computer);
	boolean update(Computer computer);
	boolean delete(Computer computer);
	List<Computer> getbyName(String search);
	List<Computer> orderBy();
	
	//Ici on peut continuer à définir des méthodes et on les appelerait
	//
}
