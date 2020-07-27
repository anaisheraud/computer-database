package com.excilys.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Selenium {
	
	/**
	 * Framework web, automatisation des tests d'applications Web
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.setProperty("webdriver.gecko.driver", "/home/heraud/Bureau/geckodriver-v0.26.0-linux64/geckodriver");
		
		WebDriver driver = new FirefoxDriver();
		
		driver.get("http://localhost:8080/computer__database/listComputers");
		
	}

}

