/*
 * On définit les méthodes ici sans les implémenter
 * c'est l'intermédiaire de Dao
 * Et on définira les méthodes dans notre implémentation CompanyDaoImpl
 */
package com.excilys.dao;

import java.util.List;

import com.excilys.beans.Company;

public interface CompanyDao {
	//Ajoute une compagnie et liste les compagnies
	//
	List<Company> lister();
	
	//Ici on peut continuer à définir des méthodes et on les appelerait
	//
}
