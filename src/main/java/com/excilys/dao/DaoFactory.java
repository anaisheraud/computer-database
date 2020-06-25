/*
 * La Factory va initialiser le dao
 * C'est ici qu'on va faire la connexion à la bdd
 * et précharger un objet en mémoire ou la connexion est déjà faite
 */
package com.excilys.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
	
	private static final String url = "jdbc:mysql://localhost/computer-database-db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	 
	private static final String username = "admincdb";
	private static final String password = "qwerty1234";

	public DaoFactory(){
		
	}

	//on appelle une méthode statique getInstance qui charge 
	//le driveur idbc effectue une connexion à la base de données:
	
	//c'est ici que se fait la connexion
	public static DaoFactory getInstance() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			
		}
		
		DaoFactory instance = new DaoFactory();
		return instance;
	}
		
	//on a une méthode getConnection qui permet à tout moment 
	//de récupérer la connexion à la base de données
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
	
	//Récupération du Dao (représenter les tables de notre BDD)
	//retourne l'implémentation et renvoie la factory elle-même
	//et l'implémentation puisse accéder à la bdd connecté
	public ComputerDao getComputerDao() {
		return new ComputerDaoImpl(this);
	}
	public CompanyDao getCompanyDao() {
		return new CompanyDaoImpl(this);
	}
	
}
