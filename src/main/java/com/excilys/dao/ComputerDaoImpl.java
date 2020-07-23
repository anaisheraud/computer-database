package com.excilys.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.beans.Computer;
import com.excilys.mappers.DateMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ComputerDaoImpl implements ComputerDao {

	/**
	 * Accès direct à l'objet connecté Récupération de la factory
	 */

	//private DaoFactory daoFactory;
	private SessionFactory daoFactory;
	
	private Logger logger = LoggerFactory.getLogger(ComputerDaoImpl.class);

	@Autowired
	public ComputerDaoImpl(SessionFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	/**
	 * Requêtes SQL, méthodes à implémenter
	 */

	/**
	 * @return Une liste d'ordinateurs
	 */
	public List<Computer> lister() {
		
		String sqlLister = "FROM Computer";
		
		Session session = daoFactory.openSession();
		session.beginTransaction();
		
		List<Computer> computer = session.createQuery(sqlLister ,Computer.class).list(); 
		session.getTransaction().commit();
		
		session.close();
		return computer;
	}	
	
	/**
	 * Ajout d'un ordinateur
	 */
	@Override
	public void ajouter(Computer computer) {

		Session session = daoFactory.openSession();
		session.beginTransaction();
		
		session.save(computer); 
		session.getTransaction().commit();
		
		session.close();	
	}

	/**
	 * Modifie les éléments d'un ordinateur
	 */
	public boolean update(Computer computer) {

		Session session = daoFactory.openSession();
		session.beginTransaction();
		
		session.update(computer); 
		session.getTransaction().commit();
		
		session.close();
		
		return true;
	}

	/**
	 * Supprime un ordinateur
	 */
	public boolean delete(Computer computer) {
		
		Session session = daoFactory.openSession();
		session.beginTransaction();
		
		session.delete(computer);
		session.getTransaction().commit();
		
		session.close();
		
		return true;
	}

	/**
	 * Count de tous les ordinateurs présents dans la bdd
	 * @return le nombre d'ordinateurs
	 */
	public int getAll() {

		int countComputers = 0;
		
		String sqlCount = "SELECT COUNT(id) as total FROM Computer";
		
		Session session = daoFactory.openSession();
		session.beginTransaction();
		
		session.createQuery(sqlCount); 
		session.getTransaction().commit();
		
		session.close();
		
		return countComputers;
	}

	/**
	 * Retrouver un ordinateur dans la bdd, pour la méthode delete
	 * @param id
	 * @return Computer
	 */
	public Computer find(int id) {

		Computer computer = new Computer();
		
		String sqlFind = "FROM Computer WHERE id= :id";
		
		Session session = daoFactory.openSession();
		session.beginTransaction();
		
		computer = session.createQuery(sqlFind, Computer.class).setParameter("id", id).getSingleResult(); 
		
		session.getTransaction().commit();
		
		session.close();
		
		return computer;
	}

	/**
	 * Recherche un ordinateur avec son nom dans la bdd
	 * @param id
	 * @return ListComputers
	 */
	public List<Computer> getbyName(String search) {

		String sqlGetbyName = "SELECT * FROM computer WHERE name LIKE :search";

		Session session = daoFactory.openSession();
		session.beginTransaction();
		
		List<Computer> selectedComputer = session.createQuery(sqlGetbyName).list(); 
		session.getTransaction().commit();
		
		session.close();
		
		return selectedComputer;
	}
	
	/**
	 * Ordonne les ordinateurs
	 * @return ListComputers
	 */
	public List<Computer> orderBy() {
	
		String sqlOrderBy = "ORDER BY :attribute";

		Session session = daoFactory.openSession();
		session.beginTransaction();
		
		List<Computer> computers = session.createQuery(sqlOrderBy).list(); 
		session.getTransaction().commit();
		
		session.close();
		
		return computers;
	}
	
	/**
	 * En cours de modification 
	 */
	@Override
	public List<Computer> listerpage(int entier1, int entier2, int lenPage) {
		return null;
	}

}
