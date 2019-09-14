package Boundary;

import java.util.Scanner;

import Control.ClinicManager;

/**
 * Boundary class for CZ2006 Server Side application
 * 
 * @author Joseph Fung
 * @version 1.0
 * @since 2019-08-31
 */

public class TestDriver {
	
	public static void main(String[] args) {
		String userInput;
		Scanner sc = new Scanner(System.in);
		ClinicManager cm = new ClinicManager();
		//cm.printClinicAl();
		do {
			System.out.println("Please enter a Clinic name to search for!\nClinic Name:");
			userInput = sc.nextLine();
			cm.findClinic(userInput);
		} while (!userInput.equalsIgnoreCase("Exit"));
	}
}
