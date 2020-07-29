package com.excilys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.excilys.beans.Company;

/**
 * Méthodes sans implémentation
 */

@Repository
public interface CompanyDao {
	
	List<Company> lister();
	boolean delete(int company_id);
	
}