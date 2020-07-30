package com.excilys.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.models.Computer;

public class ComputerMapper {

/**
* Convertir le résultat de la bdd pour chaque ordis en objet
*/		
public static Computer getComputer(ResultSet resultSet) throws SQLException {
		
		return new Computer(resultSet.getInt("id"),
				resultSet.getString("name"),
				DateMapper.sqlDateToLocalDate(resultSet.getDate("introduced")),
				DateMapper.sqlDateToLocalDate(resultSet.getDate("discontinued")),
				resultSet.getInt("company_id"));
	}
	
}
