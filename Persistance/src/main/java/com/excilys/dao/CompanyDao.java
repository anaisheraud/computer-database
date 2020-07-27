package com.excilys.dao;

import java.util.List;

import com.excilys.beans.Company;
import com.excilys.beans.Computer;

import org.springframework.stereotype.Repository;

/**
 * Méthodes sans implémentation
 */

@Repository
public interface CompanyDao {
	
	List<Company> lister();
	boolean delete(int company_id);
	
}