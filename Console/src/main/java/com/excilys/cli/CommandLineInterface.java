package com.excilys.cli;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.beans.Computer;
import com.excilys.dao.ComputerDaoImpl;
import com.excilys.mappers.DateMapper;
import com.excilys.services.ServiceCompany;
import com.excilys.services.ServiceComputer;
import com.excilys.spring.config;

/**
 * Point d'entrée d'application
 * @author heraud
 *
 */
public class CommandLineInterface {
	
	ApplicationContext context = new AnnotationConfigApplicationContext(config.class);

	private ServiceComputer serviceComputer = context.getBean(ServiceComputer.class);
	private ServiceCompany serviceCompany = context.getBean(ServiceCompany.class);
	
	private static Scanner scanner = new Scanner(System.in);
	private static Logger logger = LoggerFactory.getLogger(ComputerDaoImpl.class);

	/**
	 * Caractéristiques différentes pour le client
	 */
	public void Features() {

		System.out.println("Features? : ");
		System.out.println("List computers \n");
		System.out.println("List companies \n");
		System.out.println("Show computer details \n");
		System.out.println("Create a computer \n");
		System.out.println("Update a computer \n");
		System.out.println("Delete a computer \n");
		System.out.println("Quit \n");

		select();

	}

	public void select() {

		logger.info("computer_database");

		boolean quit = true;

		Scanner scanner = new Scanner(System.in);
		String features;

		while (quit) {
			features = scanner.nextLine();
			switch (features) {
			
//			case ("ListCt"): 
//				ListComputers10(); 
//			break;
			 
			case ("ListCp"):
				listComputers();
				break;

			case ("ListCn"):
				listCompanies();
				break;

			case ("ShowComp"):
				showComputerdetails();
				break;

			case ("Create"):
				createComputer();
				break;

			case ("Update"):
				updateComputer();
				break;

			case ("DeleteCp"):
				deleteComputer();
				break;

			case ("DeleteCn"):
				deleteCompany();
				break;

			case ("Adios"):
				System.out.println("See you soon !");
				quit = false; // stop la connexion
				break;

			default:
				System.out.println("There's not this feature ");
				break;

			}

		}
		scanner.close();

	}

	/* Appelle des méthodes */

	// Affiche la liste de tous les ordinateurs par page
	
//	  private void ListComputers10() { // TODO Auto-generated method stub
//	  
//	  int entier1 = 1; int entier2 = 10;
//	  
//	  System.out.println("List computers"); ServiceComputer.listerpage(entier1,
//	  entier2, 1);
//	  
//	  Scanner scanner = new Scanner(System.in);
//	  System.out.println("Press key b before");
//	  System.out.println("Press key n next");
//	  System.out.println("Press key q quit");
//	  
//	  String change = scanner.next();
//	  
//	  while(!change.equals("q")) {
//	  
//	  if(change.equals("b")) {
//	  
//	  entier1 = entier1 - 10; entier2 = entier2 - 10;
//	  
//	  if(entier1 < 1 && entier2 < 10) {
//	  
//	  ServiceComputer.listerpage(1, 10, 1); 
//	  entier1 = 1; 
//	  entier2 = 10;
//	  
//	  }
//	  
//	  ServiceComputer.listerpage(entier1, entier2);
//	  
//	  } else if(change.equals("n")) {
//	  
//	  entier1 = entier1 + 10; entier2 = entier2 + 10;
//	  ServiceComputer.listerpage(entier1, entier2);
//	  
//	  }
//	  
//	  change = scanner.next();
//	  
//	  }
//	  
//	 }
	 
	
	/**
	 * Affiche la liste de toutes les ordinateurs
	 * @return 
	 */
	private void listComputers() {
		// TODO Auto-generated method stub

		System.out.println("List computers" + "/n" + serviceComputer.lister());
	}
	
	/**
	 * Affiche la liste de toutes les compagnies
	 */
	private void listCompanies() {
		// TODO Auto-generated method stub

		System.out.println("List companies" + "/n" + serviceCompany.lister());

	}

	/**
	 * Affiche les détails d'un ordinateur
	 */
	private void showComputerdetails() {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		System.out.println("Id of computer ? ");

		int id = scanner.nextInt();

		System.out.println("Show computer details" + "/n" + serviceComputer.showdetails(id));

	}

	/**
	 * Créer un ordinateur
	 */
	private void createComputer() {
		// TODO Auto-generated method stub

		Computer computer = new Computer();

		Scanner scanner = new Scanner(System.in);
		System.out.println("Id ?");
		int id = scanner.nextInt();

		System.out.println("Name ?");
		String name = scanner.next();

		System.out.println("Date introduced ? format date **-**-** ");
		try {
			String introduced = scanner.next();
			computer.setIntroduced(DateMapper.stringToLocalDate(introduced));
		} catch (Exception e) {
			System.out.println("La date n'est pas conforme !");
		}

		System.out.println("Date discontinued ? format date **-**-**");
		try {
			String discontinued = scanner.next();
			computer.setDiscontinued(DateMapper.stringToLocalDate(discontinued));
		} catch (Exception e) {
			System.out.println("La date n'est pas conforme !");
		}

		System.out.println("Company_id ?");
		int company_id = scanner.nextInt();

		Computer computer1 = new Computer(id, name, computer.getIntroduced(), computer.getDiscontinued(), company_id);

		serviceComputer.create(computer);

		System.out.println("Create a computer");

	}

	/**
	 * Modifie un ordinateur
	 */
	private void updateComputer() {
		// TODO Auto-generated method stub

		Computer computer = new Computer();

		Scanner scanner = new Scanner(System.in);
		System.out.println("Id ?");
		int id = scanner.nextInt();

		System.out.println("Name ?");
		String name = scanner.next();

		System.out.println("Date introduced ? format date **-**-**");
		try {
			String introduced = scanner.next();
			computer.setIntroduced(DateMapper.stringToLocalDate(introduced));
		} catch (Exception e) {
			System.out.println("La date n'est pas conforme !");
		}

		System.out.println("Date discontinued ? format date **-**-**");
		try {
			String discontinued = scanner.next();
			computer.setDiscontinued(DateMapper.stringToLocalDate(discontinued));
		} catch (Exception e) {
			System.out.println("La date n'est pas conforme !");
		}

		System.out.println("Company_id ?");
		int company_id = scanner.nextInt();

		Computer computer1 = new Computer(id, name, computer.getIntroduced(), computer.getDiscontinued(), company_id);

		System.out.println("Update a computer");
		serviceComputer.update(computer);
		 
		System.out.println("It's ok ! Computer is update ");

	}

	/**
	 * Supprime un ordinateur
	 */
	private void deleteComputer() {
		// TODO Auto-generated method stub

		 Scanner scanner = new Scanner(System.in);
		 System.out.println("Id ?");
		 int id = scanner.nextInt();
		 
		 Computer computer = new Computer();
		
		 serviceComputer.delete(computer);
		 
		System.out.println("Delete a computer");

	}

	/**
	 * Supprime une compagnie
	 */
	private void deleteCompany() {
		// TODO Auto-generated method stub
		
		 Scanner scanner = new Scanner(System.in);
		 System.out.println("Id ?");
		 int id = scanner.nextInt();

		System.out.println("Delete a company");
		serviceCompany.delete(id);

	}

}
