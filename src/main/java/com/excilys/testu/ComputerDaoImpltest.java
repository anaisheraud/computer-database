/* Une classe (des méthodes) par test et une seule méthode par test */
package com.excilys.testu;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeNotNull;

import org.junit.Test;

import com.excilys.beans.Computer;
import com.excilys.dao.DaoFactory;

public class ComputerDaoImpltest {

	/**
	 * Test pour voir si on récupère bien un ordi
	 */
	@Test 
	public void testComputer() {
		DaoFactory daofactory = new DaoFactory(); 
		Computer computer = new Computer(); 
		assertEquals("Test fail !", computer.getClass(), daofactory.getComputerDao().lister().get(5).getClass());
	}
	
	/**
	 * Test de la méthode list "vide"
	 */
	@Test
	public void testList() {
		DaoFactory daofactory = new DaoFactory();
		assertFalse(daofactory.getComputerDao().lister().isEmpty());
		System.out.println("@Test - List isn't empty !");
	}
	
	/**
	 * Test - vérifie qu'aucun des paramètres (ici la liste) n'est égal à null
	 */
	@Test
	public void testnotnull() {
		DaoFactory daofactory = new DaoFactory();
		assumeNotNull(daofactory.getComputerDao().lister());
	}
	

}
