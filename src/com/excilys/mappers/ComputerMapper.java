/*
 * Convertir le résultat de la bdd pour chaque ordis en objet 
 */
package com.excilys.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.beans.Computer;

public class ComputerMapper {

public static Computer getComputer(ResultSet resultSet) throws SQLException {
		
		return new Computer(resultSet.getInt("id"),
				resultSet.getString("name"),
				resultSet.getDate("introduced"),
				resultSet.getDate("discontinued"),
				resultSet.getInt("company_id"));
	}
	
}
