/*
 * Convertir le résultat de la bdd pour chaque ordis en objet 
 */
package com.excilys.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.beans.Company;



public class CompanyMapper {

public static Company getCompany(ResultSet resultSet) throws SQLException {
		
		return new Company(resultSet.getInt("id"),
				resultSet.getString("name"));
	}
	
}