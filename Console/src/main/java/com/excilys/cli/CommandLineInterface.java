package com.excilys.cli;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.dao.ComputerDaoImpl;
import com.excilys.mappers.DateMapper;
import com.excilys.models.Computer;
import com.excilys.services.ServiceCompany;
import com.excilys.services.ServiceComputer;
import com.excilys.spring.Config;

/**
 * Point d'entrée d'application
 * @author heraud
 *
 */
public class CommandLineInterface {
	
	ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

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
	
	/**
	 * Affiche la liste de toutes les ordinateurs
	 * @return 
	 */
	private void listComputers() {

		System.out.println("List computers" + "/n" + serviceComputer.lister());
	}
	
	/**
	 * Affiche la liste de toutes les compagnies
	 */
	private void listCompanies() {

		System.out.println("List companies" + "/n" + serviceCompany.lister());

	}

	/**
	 * Affiche les détails d'un ordinateur
	 */
	private void showComputerdetails() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Id of computer ? ");

		int id = scanner.nextInt();

		System.out.println("Show computer details" + "/n" + serviceComputer.showdetails(id));

	}

	/**
	 * Créer un ordinateur
	 */
	private void createComputer() {

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
		
		 Scanner scanner = new Scanner(System.in);
		 System.out.println("Id ?");
		 int id = scanner.nextInt();

		System.out.println("Delete a company");
		serviceCompany.delete(id);

	}

}
