package services;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.excilys.beans.Computer;
import com.excilys.dao.DaoFactory;

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
		 
		 Scanner scanner = new Scanner(System.in);
		 System.out.println("Id ?");
		 int id = scanner.nextInt();
		 
		 System.out.println("Name ?");
		 String name = scanner.next();
		 
		 System.out.println("Introduced ?");
		 String introduced = scanner.next();
		 
		 System.out.println("Discontinued ?");
		 String discontinued = scanner.next();
		 
		 System.out.println("Company_id ?");
		 int company_id = scanner.nextInt();
		 
		 Computer computer = new Computer(id, name, (Date.valueOf(introduced)), (Date.valueOf(discontinued)), company_id);
		
		 daoconnexion.getComputerDao().ajouter(computer);
		 
	 }
	 
	 public static void update() {
		 
		 Scanner scanner = new Scanner(System.in);
		 System.out.println("Id ?");
		 int id = scanner.nextInt();
		 
		 System.out.println("Name ?");
		 String name = scanner.next();
		 
		 System.out.println("Introduced ?");
		 String introduced = scanner.next();
		 
		 System.out.println("Discontinued ?");
		 String discontinued = scanner.next();
		 
		 System.out.println("Company_id ?");
		 int company_id = scanner.nextInt();
		 
		 Computer computer = new Computer(id, name, (Date.valueOf(introduced)), (Date.valueOf(discontinued)), company_id);
		 
		 daoconnexion.getComputerDao().update(computer);
		 
		 if(daoconnexion.getComputerDao().update(computer)) {
		 
		 System.out.println("Update successful.");

		 } else {

		 System.out.println("Update impossible.");

		 }
		 
	 }
	 
	 public static void delete() {
		 
		 Scanner scanner = new Scanner(System.in);
		 System.out.println("Id ?");
		 int id = scanner.nextInt();
		 
		 Computer computer = new Computer();
		 computer.setId(id);
		 
		 daoconnexion.getComputerDao().delete(computer);
		 
	 }
	 
}
