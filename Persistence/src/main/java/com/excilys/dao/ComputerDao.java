package com.excilys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.excilys.models.Computer;

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
