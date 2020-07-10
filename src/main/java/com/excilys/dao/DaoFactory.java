package com.excilys.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Component;

/**
 * Factory initialise l'objet dao, connexion à la bdd
 *
 */

@Component
public class DaoFactory {
	
    private HikariDataSource ds;
    
    private HikariConfig config;

	public DaoFactory(){
		config = new HikariConfig("/hikari.properties");
		ds = new HikariDataSource(config);
	}
	
	/**
	 * Récupère la connexion à la bdd
	 * @return
	 * @throws SQLException
	 */
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
	
    /**
     * Accès à la bdd connecté 
     * @return l'implémentation des méthodes des interfaces
     */
	public ComputerDao getComputerDao() {
		return new ComputerDaoImpl(this);
	}
	public CompanyDao getCompanyDao() {
		return new CompanyDaoImpl(this);
	}
	
}
