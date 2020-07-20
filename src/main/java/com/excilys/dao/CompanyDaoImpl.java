package com.excilys.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
		
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(daoFactory.getDs());
		
		RowMapper<Company> rowMapper = new RowMapper<Company>()	{
			
			public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Company company = new Company();
			company.setId(rs.getInt("company.id"));
			company.setName(rs.getString("company.name"));
			
		    return company; 
		    
			}
			
		};
		
		String sqlLister = "SELECT id, name FROM company;";
		companies = jdbcTemplate.query(sqlLister, rowMapper);
		
		return companies;
	}
	
	/**
	 * Supprime une compagnie
	 */
	public boolean delete(int company_id) {
	
	NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(daoFactory.getDs());
	
	try {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", company_id);
		   
		String sqlDelete = "DELETE FROM company WHERE company_id = ?";
	    jdbcTemplate.update(sqlDelete, param);
	    
	} catch (DataAccessException e) {
		e.printStackTrace();
		logger.error("Company isn't deleted");
		return false;
	}
	return true;
	}
}