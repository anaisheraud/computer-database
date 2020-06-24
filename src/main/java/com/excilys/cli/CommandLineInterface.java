/* Point d'entrée dans l'application */

package com.excilys.cli;

import java.util.Scanner;

import services.ServiceCompany;
import services.ServiceComputer;

public class CommandLineInterface {
	
	private static ServiceComputer serviceComputer = new ServiceComputer();
    private static ServiceCompany serviceCompany = new ServiceCompany();
    private static Scanner scanner = new Scanner(System.in);
	
    
    //Affiche les caractéristiques différentes pour le client
	public static void Features() {

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
	
	//Au lancement du programme, le client va rentrer un chiffre au clavier 
	//Ci-dessous
		public static void select() {
			
			boolean quit = true;
		
			Scanner scanner = new Scanner(System.in);
			String features; 

			while(quit) { 
				features = scanner.nextLine();
				switch(features) { 
				
				case ("ListCt"):
					ListComputers();
					break;
					
				case ("ListCn"):
					ListCompanies();
					break;
					
				case ("ShowComp"):
					ShowComputerdetails();
					break;
					
				case ("Create"):
					CreateComputer();
					break;
					
				case ("Update"):
					UpdateComputer();
					break;
					
				case ("Delete"):
					DeleteComputer();
					break;
					
				case ("Adios"):
					System.out.println("See you soon !");
					quit = false; //stop la connexion
					break;
				
				default:
					System.out.println("There's not this feature ");
					break;
				
				}	
				
			}
		 	scanner.close();

		}
		
		/* Appelle des méthodes */

		//Affiche la liste de tous les ordinateurs par page
		private static void ListComputers() {
			// TODO Auto-generated method stub

			int entier1 = 1;
			int entier2 = 20;
			
			 System.out.println("List computers");
			 ServiceComputer.listerpage(entier1, entier2);
			 
			 Scanner scanner = new Scanner(System.in);
			 System.out.println("Press key b before");
			 System.out.println("Press key n next");
			 System.out.println("Press key q quit");
			 
			 String change = scanner.next();
			 	 
			 while(!change.equals("q")) {
				 
				 if(change.equals("b")) { 
					 
					 entier1 = entier1 - 20;
					 entier2 = entier2 - 20;
					 
					 if(entier1 < 1 && entier2 < 20) {
						 
						 ServiceComputer.listerpage(1, 20);
						 entier1 = 1;
						 entier2 = 20;
						 
					 } 
					 
					 ServiceComputer.listerpage(entier1, entier2);
					 
				 } else if(change.equals("n")) {
						 
					 entier1 = entier1 + 20;
					 entier2 = entier2 + 20;	
					 ServiceComputer.listerpage(entier1, entier2);
					 
				 }
				 
				 change = scanner.next(); 
				 
			 }
				 		 	 
		}


	//Affiche la liste de toutes les compagnies 
		private static void ListCompanies() {
			// TODO Auto-generated method stub
			
			 System.out.println("List companies");
			 ServiceCompany.lister();
		}

	//Affiche les détails d'un ordinateur
		private static void ShowComputerdetails() {
			// TODO Auto-generated method stub
	
		System.out.println("Show computer details");
		ServiceComputer.showdetails();
	
	}

	//Créer un ordinateur
	private static void CreateComputer() {
			// TODO Auto-generated method stub
		 	 
			System.out.println("Create a computer");
			ServiceComputer.create();
		
		}

	//Modifie un ordinateur 
	private static void UpdateComputer() {
		// TODO Auto-generated method stub

		System.out.println("Update a computer");
	 	ServiceComputer.update();
		
	}

	//Supprime un ordinateur 
	private static void DeleteComputer() {
		// TODO Auto-generated method stub
	
		 System.out.println("Delete a computer");
		 ServiceComputer.delete();
		 
	}
			
}
