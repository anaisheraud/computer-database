package com.excilys.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.excilys.beans.Company;
import com.excilys.mappers.CompanyMapper;

public class CompanyDaoImpl implements CompanyDao {
	
	// quand on instancie les objets, on remarque qu'on récupère 
	// la factory et nous donne accès directement à l'objet connecté
	private DaoFactory daoFactory;

	CompanyDaoImpl(DaoFactory daoFactory){
		this.daoFactory = daoFactory;
	}
	
	// et ensuite on retrouve la méthode juste lister pour company
	// qui en fait implémente l'interface 
	// et qu'on retrouve ainsi nos requête sql : 
	// insert into, select, from..
	
	// 
	@Override
	//retourne void 
	
	public List<Company> lister(){
		// retourne une liste d'utilisateurs
		List<Company> companies = new ArrayList<Company>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT id, name FROM company;");
			
			while (resultat.next()) {

				Company company = CompanyMapper.getCompany(resultat);
				companies.add(company);
				
			}
		} catch (SQLException e) {
        e.printStackTrace();
    }
    return companies;
	}
	
}