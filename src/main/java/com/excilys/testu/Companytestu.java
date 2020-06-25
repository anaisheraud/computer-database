package com.excilys.testu;

import static org.junit.Assert.*;

import org.junit.Test;

import com.excilys.beans.Company;


public class Companytestu {
	
	@Test
	public void TestGetId(){ 
		
		Company company = new Company(1, "Apple Inc.");
		assertTrue("GetId fail", company.getId()==1);
		
	}	
	
	@Test
	public void TestSetName() {
		
		Company company = new Company();
		company.setName("RCA");
		assertTrue("SetName fail", company.getName()=="RCA");
		
	}
	
}


