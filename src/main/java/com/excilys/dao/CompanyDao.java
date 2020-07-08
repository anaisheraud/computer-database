/*
 * On définit les méthodes ici sans les implémenter
 * c'est l'intermédiaire de Dao
 * Et on définira les méthodes dans notre implémentation CompanyDaoImpl
 */
package com.excilys.dao;

import java.util.List;

import com.excilys.beans.Company;
import com.excilys.beans.Computer;

import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDao {
	//Ajoute une compagnie et liste les compagnies
	//
	List<Company> lister();
	boolean delete(int company_id);
	
	//Ici on peut continuer à définir des méthodes et on les appelerait
	//
}