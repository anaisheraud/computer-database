package com.excilys.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.beans.Computer;
import com.excilys.mappers.CompanyMapper;
import com.excilys.mappers.ComputerMapper;
import com.excilys.mappers.DateMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ComputerDaoImpl implements ComputerDao {

	/**
	 * Accès direct à l'objet connecté Récupération de la factory
	 */

	@Autowired
	private DaoFactory daoFactory;
	private Logger logger = LoggerFactory.getLogger(ComputerDaoImpl.class);

	public ComputerDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	/**
	 * Requêtes SQL, méthodes à implémenter
	 */

	/**
	 * @return Une liste d'ordinateurs
	 */
	public List<Computer> lister() {
		
		List<Computer> computers = null;
		
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(daoFactory.getDs());
		
		RowMapper<Computer> rowMapper = new RowMapper<Computer>()	{
			
			public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Computer computer = new Computer();
			computer.setId(rs.getInt("computer.id"));
			computer.setName(rs.getString("computer.name"));
			computer.setIntroduced(DateMapper.sqlDateToLocalDate(rs.getDate("computer.introduced")));
			computer.setDiscontinued(DateMapper.sqlDateToLocalDate(rs.getDate("computer.discontinued")));
			computer.setCompany_id(rs.getInt("computer.id"));
			
		    return computer; 
		    
			}
			
		};
		
		String sqlLister = "SELECT id, name, introduced, discontinued, company_id FROM computer";
		computers = jdbcTemplate.query(sqlLister, rowMapper);
		
		return computers;
	}

	
	/**
	 * Ajout d'un ordinateur
	 */
	@Override
	public void ajouter(Computer computer) {

		try {
			MapSqlParameterSource param = new MapSqlParameterSource();
		    param.addValue("name", computer.getName());
		    param.addValue("introduced", DateMapper.localDateTosqlDate(computer.getIntroduced()));
		    param.addValue("discontinued", DateMapper.localDateTosqlDate(computer.getDiscontinued()));
		    param.addValue("company_id", computer.getCompany_id());
			
		    NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(daoFactory.getDs());
		    String sqlAjouter = "INSERT INTO computer(name, introduced, discontinued, company_id) VALUES (:name,:introduced,:discontinued,:company_id);";
		    jdbcTemplate.update(sqlAjouter, param);
	
		} catch (DataAccessException e) {
			logger.error("Computer isn't added");
			e.printStackTrace();
		}
	}

	/**
	 * Modifie les éléments d'un ordinateur
	 */
	public boolean update(Computer computer) {

		try {
			logger.info(computer.getId() + " " + computer.getName() + " " + computer.getCompany_id());
			
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("id", computer.getId());
		    param.addValue("name", computer.getName());
		    param.addValue("introduced", DateMapper.localDateTosqlDate(computer.getIntroduced()));
		    param.addValue("discontinued", DateMapper.localDateTosqlDate(computer.getDiscontinued()));
		    param.addValue("company_id", computer.getCompany_id());
		    
		    NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(daoFactory.getDs());
		    String sqlUpdate = "UPDATE computer SET name = :name, introduced = :introduced, discontinued = :discontinued, company_id = :company_id WHERE id = :id;";
		    jdbcTemplate.update(sqlUpdate, param);
			
			} catch (DataAccessException e) {
				logger.error("Computer isn't updated");
				e.printStackTrace();
				return false;
			}
		return true;
	}

	/**
	 * Supprime un ordinateur
	 */
	public boolean delete(Computer computer) {

		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(daoFactory.getDs());
		
		try {
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("id", computer.getId());
			   
			String sqlDelete = "DELETE FROM computer WHERE id = :id";
		    jdbcTemplate.update(sqlDelete, param);
		    
		    System.out.println(computer.getId());
		    
		} catch (DataAccessException e) {
			e.printStackTrace();
			logger.error("Computer isn't deleted");
			return false;
		}
		return true;
	}

	/**
	 * Count de tous les ordinateurs présents dans la bdd
	 * @return le nombre d'ordinateurs
	 */
	public int getAll() {

		int countComputers = 0;
		
			JdbcTemplate jdbcTemplate = new JdbcTemplate(daoFactory.getDs());
		    String sqlCount = "SELECT COUNT(id) as total FROM computer;";
			
		    countComputers = jdbcTemplate.queryForObject(sqlCount, Integer.class);
		
		return countComputers;
	}

	/**
	 * Retrouver un ordinateur dans la bdd, pour la méthode delete
	 * @param id
	 * @return Computer
	 */
	public Computer find(int id) {

		Computer computer = new Computer();
		
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(daoFactory.getDs());
			
		try {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
		
		RowMapper<Computer> rowMapper = new RowMapper<Computer>()	{
			
			public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Computer computer = new Computer();
			computer.setId(rs.getInt("computer.id"));
			computer.setName(rs.getString("computer.name"));
			computer.setIntroduced(DateMapper.sqlDateToLocalDate(rs.getDate("computer.introduced")));
			computer.setDiscontinued(DateMapper.sqlDateToLocalDate(rs.getDate("computer.discontinued")));
			computer.setCompany_id(rs.getInt("computer.id"));
			
		return computer;
			}
		};
		
		String sqlFind = "SELECT * FROM computer WHERE id= :id;";
		   
		computer = jdbcTemplate.queryForObject(sqlFind, param, rowMapper);

		} catch (DataAccessException e) {
			logger.error("Computer not found !");
			e.printStackTrace();
		}

		return computer;
	}

	/**
	 * Recherche un ordinateur avec son nom dans la bdd
	 * @param id
	 * @return ListComputers
	 */
	public List<Computer> getbyName(String search) {

		List<Computer> selectedComputer = new ArrayList<Computer>();
		
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(daoFactory.getDs());
		
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("search", search);
		
		RowMapper<Computer> rowMapper = new RowMapper<Computer>()	{	
			
			public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Computer computer = new Computer();
				computer.setId(rs.getInt("computer.id"));
				computer.setName(rs.getString("computer.name"));
				computer.setIntroduced(DateMapper.sqlDateToLocalDate(rs.getDate("computer.introduced")));
				computer.setDiscontinued(DateMapper.sqlDateToLocalDate(rs.getDate("computer.discontinued")));
				computer.setCompany_id(rs.getInt("computer.id"));
				
			    return computer;
			    
			}
			
		};
		
		    String sqlGetbyName = "SELECT * FROM computer WHERE name LIKE :search";

		    selectedComputer = jdbcTemplate.query(sqlGetbyName, param, rowMapper);
	
		return selectedComputer;
	}

	/**
	 * Ordonne les ordinateurs
	 * @return ListComputers
	 */
	public List<Computer> orderBy() {
	
		List<Computer> computers = new ArrayList<Computer>();
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(daoFactory.getDs());
		
		RowMapper<Computer> rowMapper = new RowMapper<Computer>()	{	
			
			public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Computer computer = new Computer();
				computer.setId(rs.getInt("computer.id"));
				computer.setName(rs.getString("computer.name"));
				computer.setIntroduced(DateMapper.sqlDateToLocalDate(rs.getDate("computer.introduced")));
				computer.setDiscontinued(DateMapper.sqlDateToLocalDate(rs.getDate("computer.discontinued")));
				computer.setCompany_id(rs.getInt("computer.id"));
				
			    return computer;
			    
			}
			
		};
	
			String sqlOrderBy = "SELECT id, name, introduced, discontinued, company_id FROM computer ORDER BY name;";
		    
		    computers = jdbcTemplate.query(sqlOrderBy, rowMapper);
		    
		return computers;
	}
	
	

	/**
	 * En cours de modification 
	 */
	@Override
	public List<Computer> listerpage(int entier1, int entier2, int lenPage) {
		// TODO Auto-generated method stub
		return null;
	}

}
