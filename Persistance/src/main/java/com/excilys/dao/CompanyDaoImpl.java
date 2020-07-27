package com.excilys.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.beans.Company;
import com.excilys.beans.Computer;
import com.excilys.mappers.CompanyMapper;
import com.excilys.mappers.DateMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDaoImpl implements CompanyDao {
	
	private Logger logger = LoggerFactory.getLogger(CompanyDaoImpl.class);
	
	/**
	 * Accès direct à l'objet connecté 
	 * Récupération de la factory
	 */

	private SessionFactory daoFactory;

	@Autowired
	public CompanyDaoImpl(SessionFactory daoFactory) {
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
		
		String sqlLister = "FROM Company";
		
		Session session = daoFactory.openSession();
		session.beginTransaction();
		List<Company> company = session.createQuery(sqlLister ,Company.class).list(); 
		session.getTransaction().commit();
		session.close();
		
		return company;
	}
	
	/**
	 * Supprime une compagnie
	 */
	public boolean delete(int company_id) {
	
		Session session = daoFactory.openSession();
		session.beginTransaction();	
		session.delete(company_id);
		session.getTransaction().commit();
		session.close();
		
		return true;
	}
}