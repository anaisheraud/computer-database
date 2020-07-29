package com.excilys.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.beans.Company;

@Repository
public class CompanyDaoImpl implements CompanyDao {
	
	//private Logger logger = LoggerFactory.getLogger(CompanyDaoImpl.class);
	
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

	String sqlLister = "FROM Company";
	
	/**
	 * @return Une liste de compagnies
	 */
	@Override
	public List<Company> lister() {
		
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