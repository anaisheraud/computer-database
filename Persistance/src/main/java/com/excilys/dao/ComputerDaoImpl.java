package com.excilys.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.beans.Computer;

@Repository
public class ComputerDaoImpl implements ComputerDao {

	//private Logger logger = LoggerFactory.getLogger(ComputerDaoImpl.class);
	
	/**
	 * Accès direct à l'objet connecté Récupération de la factory
	 */

	private SessionFactory daoFactory;

	@Autowired
	public ComputerDaoImpl(SessionFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	/**
	 * Requêtes SQL, méthodes à implémenter
	 */
	
	String sqlLister = "FROM Computer";
	String sqlFind = "FROM Computer WHERE id= :id";
	String sqlCount = "SELECT COUNT(id) as total FROM Computer";
	String sqlGetbyName = "FROM Computer WHERE name LIKE :search";
	
	/**
	 * @return Une liste d'ordinateurs
	 */
	@Override
	public List<Computer> lister() {
		
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
	 * Retrouver un ordinateur dans la bdd, pour la méthode delete
	 * @param id
	 * @return Computer
	 */
	public Computer find(int id) {
		
		Computer computer = new Computer();
		Session session = daoFactory.openSession();
		session.beginTransaction();
		computer = session.createQuery(sqlFind, Computer.class)
				.setParameter("id", id).getSingleResult(); 
		session.getTransaction().commit();
		session.close();
		
		return computer;
	}
	
	/**
	 * Count de tous les ordinateurs présents dans la bdd
	 * @return le nombre d'ordinateurs
	 */
	public int getAll() {
		
		Session session = daoFactory.openSession();
		session.beginTransaction();
		Long countComputers = session.createQuery(sqlCount, Long.class).getSingleResult(); 
		session.getTransaction().commit();
		session.close();
		
		return countComputers.intValue();
	}

	/**
	 * Recherche un ordinateur avec son nom dans la bdd
	 * @param search
	 * @return SelectedComputers
	 */
	public List<Computer> getbyName(String search) {

		Session session = daoFactory.openSession();
		session.beginTransaction();	
		List<Computer> selectedComputer = session.createQuery(sqlGetbyName, Computer.class)
				.setParameter("search", search).list(); 
		session.getTransaction().commit();
		session.close();
		
		return selectedComputer;
	}
	
	/**
	 * Ordonne les ordinateurs
	 * @return ListComputers
	 */
	public List<Computer> orderBy() {
		String sqlOrderBy = "FROM Computer ORDER BY name";

		Session session = daoFactory.openSession();
		session.beginTransaction();
		List<Computer> computers = session.createQuery(sqlOrderBy, Computer.class).list(); 
		session.getTransaction().commit();	
		session.close();
		
		return computers;
	}
	
	/**
	 * Lister les pages
	 * @return computer
	 */
	public List<Computer> listerpage(int entier1, int entier2) {
		
		Session session = daoFactory.openSession();
		session.beginTransaction();
		List<Computer> computer = session.createQuery(sqlLister ,Computer.class)
				.setFirstResult(entier1)
				.setMaxResults(entier2).list();
		session.getTransaction().commit();
		session.close();
		
		return computer;
	}

}
