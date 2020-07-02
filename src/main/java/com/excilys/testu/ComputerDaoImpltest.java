/* Une classe (des méthodes) par test et une seule méthode par test */
package com.excilys.testu;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeNotNull;

import org.junit.Test;

import com.excilys.beans.Computer;
import com.excilys.dao.DaoFactory;


public class ComputerDaoImpltest {

	@Test /* La méthode public auquel il est attaché peut être exécuté comme un cas de test */
	
	/* Test pour bien être sur de récupérer un ordi */
	// Récupération d'une liste d'ordinateurs de la classe computer 
	// en vérifiant que la méthode lister me renvoie des ordinateurs 
	// en prenant un objet dedans la liste d'objets
	public void testComputer() {
		
		DaoFactory daofactory = DaoFactory.getInstance(); //connexion avec la bdd
		Computer computer = new Computer(); //création d'un ordi
		
		assertEquals("Test fail !", computer.getClass(), daofactory.getComputerDao().lister().get(5).getClass());
		
		//fail("Not yet implemented");
	}
	
	/* Test de la méthode list */
	@Test
	public void testList() {
		
		DaoFactory daofactory = DaoFactory.getInstance();
		
		assertFalse(daofactory.getComputerDao().lister().isEmpty());
		
		System.out.println("@Test - List isn't empty !");
		
	}
	
	/* Test de la méthode page */
	@Test 
	public void testPage() {
		
		DaoFactory daofactory = DaoFactory.getInstance();
		
		int tailleattendue = 20;
		
		int tailletest = daofactory.getComputerDao().listerpage(1, 21, 1).size(); 
		
		assertEquals("Test fail !", tailleattendue, tailletest);
		
	}
	
	/* Test - vérifie qu'aucun des paramètres (ici la liste) n'est égal à null */
	@Test
	public void testnotnull() {
		
		DaoFactory daofactory = DaoFactory.getInstance();
		
		assumeNotNull(daofactory.getComputerDao().lister());
		
	}
	

}
