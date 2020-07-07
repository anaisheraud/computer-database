/*
 * On définit les méthodes ici sans les implémenter
 */
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

public class ComputerDaoImpl implements ComputerDao {
	
	// quand on instancie les objets, on remarque qu'on récupère 
	// la factory et nous donne accès directement à l'objet connecté
	private static DaoFactory daoFactory;
	private static Logger logger = LoggerFactory.getLogger(ComputerDaoImpl.class);

	public ComputerDaoImpl(DaoFactory daoFactory){
		this.daoFactory = daoFactory;
	}
	
	// et ensuite on retrouve les méthodes ajouter et lister
	// qui en fait implémente l'interface 
	// et qu'on retrouve ainsi nos requête sql : 
	// insert into, select, from..
	
	
	public List<Computer> lister(){
		// retourne une liste d'utilisateurs
		List<Computer> computers = new ArrayList<Computer>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		
		try {
			connexion = daoFactory.getInstance().getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT id, name, introduced, discontinued, company_id FROM computer;");
			
			while (resultat.next()) {
				
				Computer computer = ComputerMapper.getComputer(resultat);
				
				//ajout les ordinateurs de ma bdd dans une liste
				computers.add(computer);
			}
		} catch (SQLException e) {
			logger.error("List isn't displayed");
        e.printStackTrace();
    }
    return computers;
	} 
	
	public List<Computer> listerpage(int entier1, int entier2, int lenPage){
		// retourne une liste d'utilisateurs
		List<Computer> computers = new ArrayList<Computer>();
		Connection connexion = null;
		PreparedStatement preparedstatement = null;
		ResultSet resultat = null;
		
		try {
			connexion = daoFactory.getInstance().getConnection();
			preparedstatement = connexion.prepareStatement("SELECT id, name, introduced, discontinued, company_id FROM computer WHERE id >= ? AND id <= ?;");
			preparedstatement.setInt(1, entier1 + lenPage);
			preparedstatement.setInt(2, entier2 + lenPage);
			resultat = preparedstatement.executeQuery();
			
			while (resultat.next()) {
				
				Computer computer = ComputerMapper.getComputer(resultat);
				
				//ajout les ordinateurs de ma bdd dans une liste
				computers.add(computer);
				
			}
			
		} catch (SQLException e) {
			logger.error("List page isn't displayed");
        e.printStackTrace();
    }
    return computers;
	}
	
	@Override
	//retourne void 
	public void ajouter(Computer computer) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		try {
			// La particularité a noté c'est qu'on récupère l'objet : 
			// daoFactory.getConnection() qui représente la connexion 
			// comme ça on a pas besoin de refaire la connexion systématiquement
			// et on récupère la connexion qui a été fait en amont en un factory		
			connexion = daoFactory.getInstance().getConnection();
			preparedStatement = connexion.prepareStatement
					("INSERT INTO computer(id, name, introduced, discontinued, company_id) VALUES (?,?,?,?,?);");
			preparedStatement.setInt(1, computer.getId());
			preparedStatement.setString(2, computer.getName());
			preparedStatement.setDate(3, DateMapper.localDateTosqlDate(computer.getIntroduced()));
			preparedStatement.setDate(4, DateMapper.localDateTosqlDate(computer.getDiscontinued()));
			preparedStatement.setInt(5, computer.getCompany_id());
			
			preparedStatement.executeUpdate();
		}
				catch (SQLException e) {
					logger.error("Computer isn't added");
					e.printStackTrace();
			}
		}
	
	public boolean update(Computer computer) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		try {
			logger.info(computer.getId() + " " + computer.getName() + " " + computer.getCompany_id());
			// La particularité a noté c'est qu'on récupère l'objet : 
			// daoFactory.getConnection() qui représente la connexion 
			// comme ça on a pas besoin de refaire la connexion systématiquement
			// et on récupère la connexion qui a été fait en amont en un factory		
			connexion = daoFactory.getInstance().getConnection();
			preparedStatement = connexion.prepareStatement
					("UPDATE computer SET id = ?, name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?;");
			preparedStatement.setInt(1, computer.getId());
			preparedStatement.setString(2, computer.getName());
			preparedStatement.setDate(3, DateMapper.localDateTosqlDate(computer.getIntroduced()));
			preparedStatement.setDate(4, DateMapper.localDateTosqlDate(computer.getDiscontinued()));
			preparedStatement.setInt(5, computer.getCompany_id());
			preparedStatement.setInt(6, computer.getId());
			
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
					logger.error("Computer isn't updated");
					e.printStackTrace();
					return false;
			}
		return true;
		}
	
	public boolean delete(Computer computer) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		try {
			// La particularité a noté c'est qu'on récupère l'objet : 
			// daoFactory.getConnection() qui représente la connexion 
			// comme ça on a pas besoin de refaire la connexion systématiquement
			// et on récupère la connexion qui a été fait en amont en un factory		
			connexion = daoFactory.getInstance().getConnection();
			preparedStatement = connexion.prepareStatement
					("DELETE FROM computer WHERE id = ?");
			preparedStatement.setInt(1, computer.getId());
			
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
					e.printStackTrace();
					logger.error("Computer isn't deleted");
					return false;
			}
		return true;
	}
	
	  public static int getAll() {
		  	Connection connexion = null;
			PreparedStatement preparedStatement = null;
		  
			int countComputers = 0;
	        
	        try {
	        	connexion = daoFactory.getInstance().getConnection();
	            preparedStatement = connexion.prepareStatement("SELECT COUNT(id) as total FROM computer;"); 
	            System.out.println(preparedStatement);
	           
	            ResultSet resultSet = preparedStatement.executeQuery();
	            while (resultSet.next()) {
	            	countComputers = resultSet.getInt("total");
	            }
	            
	        } catch (SQLException e) {
	            logger.error("error listing all computers", e);
	        }
	        return countComputers;
	    }
	  
		 public static Computer find(int id) {
			 	Connection connexion = null;
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
			 
				Computer computer = null;
				try {
					connexion = daoFactory.getInstance().getConnection();
		            preparedStatement = connexion.prepareStatement("SELECT * FROM computer WHERE id=?;");
					
		            preparedStatement.setInt(1, id);
		            resultSet = preparedStatement.executeQuery();
		            while (resultSet.next()) {
	                    computer = ComputerMapper.getComputer(resultSet);
	                }		
						
				} catch(SQLException e) {
					logger.error("Computer not found !");
					e.printStackTrace();
				}
				
				return computer;
			}
		
		 public List<Computer> getbyName(String search) {
			 
			 	List<Computer> selectedComputers = new ArrayList<Computer>();
			 
			  	Connection connexion = null;
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
		        
		        try {
		        	connexion = daoFactory.getInstance().getConnection();
		            preparedStatement = connexion.prepareStatement("SELECT * FROM computer WHERE name LIKE ?");
		            preparedStatement.setString(1, search);
		            resultSet = preparedStatement.executeQuery();
		            
		            while (resultSet.next()) {
		            	Computer computer = ComputerMapper.getComputer(resultSet);
		            	selectedComputers.add(computer);
		            }
		            
		        } catch (SQLException e) {
		            logger.error("error search all computers", e);
		        }
		        return selectedComputers;
		 }
		 
		 public List<Computer> orderBy(){
				// retourne une liste d'utilisateurs
				List<Computer> computers = new ArrayList<Computer>();
				Connection connexion = null;
				Statement statement = null;
				ResultSet resultat = null;
				
				try {
					connexion = daoFactory.getInstance().getConnection();
					statement = connexion.createStatement();
					resultat = statement.executeQuery("SELECT id, name, introduced, discontinued, company_id FROM computer ORDER BY name;");
					
					while (resultat.next()) {
						
						Computer computer = ComputerMapper.getComputer(resultat);
						
						//ajout les ordinateurs de ma bdd dans une liste
						computers.add(computer);
					}
				} catch (SQLException e) {
					logger.error("Error order By Computer");
		        e.printStackTrace();
		    }
		    return computers;
			} 
		 
	
}
