package com.excilys.testu;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import com.excilys.beans.Computer;
import com.excilys.dao.DaoFactory;

public class Computertestu {
	
	@Test 
	public void TestGetName(){
	
		DaoFactory daofactory = new DaoFactory();
		
		Computer computer = new Computer();
		
		computer.setName("CM-5e");
		
		assertTrue("GetName fail !", computer.getName().equals(daofactory.getComputerDao().lister().get(3).getName()));
		
	}
	
	@Test 
	public void TestGetDiscontinued() {
		
		DaoFactory daofactory = new DaoFactory();
		
		LocalDate datea = LocalDate.of(1984, 04, 01);

		assertEquals("Date fail !",datea,daofactory.getComputerDao().lister().get(11).getDiscontinued());
		
		/* une autre date localdate, et le double, bus*/
	}
	
}


