package com.excilys.dao;

import java.util.List;

import com.excilys.beans.Computer;

import org.springframework.stereotype.Repository;

/**
 * Méthodes sans implémentation
 */

@Repository
public interface ComputerDao {
	
	List<Computer> listerpage(int entier1, int entier2);
	List<Computer> lister();
	void ajouter(Computer computer);
	boolean update(Computer computer);
	boolean delete(Computer computer);
	List<Computer> getbyName(String search);
	List<Computer> orderBy();
	
}
