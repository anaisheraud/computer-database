package com.excilys.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.beans.Company;
import com.excilys.beans.Computer;
import com.excilys.mappers.CompanyMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDaoImpl implements CompanyDao {
	
	/**
	 * Accès direct à l'objet connecté 
	 * Récupération de la factory
	 */

	@Autowired
	private DaoFactory daoFactory;
	private Logger logger = LoggerFactory.getLogger(CompanyDaoImpl.class);

	public CompanyDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	/**
	 * Requêtes SQL, méthodes à implémenter
	 */

	/**
	 * @return Une liste de compagnies
	 */
	@Override
	public List<Company> lister() {
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
			logger.error("List isn't displayed");
			e.printStackTrace();
		}
		return companies;
	}
	
	/**
	 * Supprime une compagnie
	 */
	public boolean delete(int company_id) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		try {	
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement
					("DELETE FROM computer WHERE company_id = ?");
			preparedStatement.setInt(1, company_id);		
			preparedStatement.executeUpdate();
			
			preparedStatement = connexion.prepareStatement
					("DELETE FROM company WHERE id = ?");
			preparedStatement.setInt(1, company_id);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
					e.printStackTrace();
					logger.error("Company isn't deleted");
					return false;
			}
		return true;
	} 

}