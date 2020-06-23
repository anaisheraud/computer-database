package services;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.excilys.beans.Computer;
import com.excilys.dao.DaoFactory;
import com.excilys.mappers.DateMapper;

public class ServiceComputer {
	
	 private static DaoFactory daoconnexion = new DaoFactory(); 
	 
	 /* 
	  * Création des méthodes computer 
	  * 
	  * */
	 
	 public static void listerpage(int entier1, int entier2) {
		 
		 List<Computer> allComputer = daoconnexion.getComputerDao().listerpage(entier1, entier2);
		 System.out.println(allComputer);
		 
	 }
	 
	 public static void lister() {
		 
		 List<Computer> allComputer = daoconnexion.getComputerDao().lister();
		 System.out.println(allComputer);
		 
	 }
	 
	 public static void showdetails() {
		 
		 Scanner scanner = new Scanner(System.in);
		 System.out.println("Id of computer ? "); 
		 
		 int Id = scanner.nextInt(); 
		 
		 System.out.println(daoconnexion.getComputerDao().lister().get(Id-1));
		 
	 }
	 
	 public static void create() {
		 
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
		
		 daoconnexion.getComputerDao().ajouter(computer1);
		 
		 System.out.println("It's ok ! Computer is created ");
		 
	 }
	 
	 public static void update() {
		 
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
			 
		 daoconnexion.getComputerDao().update(computer);
		 
		 if(daoconnexion.getComputerDao().update(computer)) {
		 
		 System.out.println("Update successful.");

		 } else {

		 System.out.println("Update impossible.");

		 }
		 
		 System.out.println("It's ok ! Computer is update ");
		 
	 }
	 
	 public static void delete() {
		 
		 Scanner scanner = new Scanner(System.in);
		 System.out.println("Id ?");
		 int id = scanner.nextInt();
		 
		 Computer computer = new Computer();
		 computer.setId(id);
		 
		 daoconnexion.getComputerDao().delete(computer);
		 
		 System.out.println("It's ok ! Computer is delete ");
		 
	 }
	 
}
