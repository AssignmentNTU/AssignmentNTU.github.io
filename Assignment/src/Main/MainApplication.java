package Main;

import java.util.ArrayList;
import java.util.Scanner;

import Cineplex.Cineplex;
import Cineplex.CineplexDatabase;
import Staff.StaffApplication;

public class MainApplication {

	public static void main(String args[]){
		CineplexDatabase cDatabase1 =  new CineplexDatabase();
		ArrayList<Cineplex> listCineplex1 = cDatabase1.readFromDatabase("CineplexDatabase.dat");
	//	System.out.println("Size of Cineplex rating : "+listCineplex1.get(2).getMovieRatingList().size());
		
		int choiceLogin = 0;
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome To MOBLIMA\n");
		System.out.println("MOBLIMA is application that allow you to book \n"
			+"your favorite movie at your favorite Cinema with ease\n"
			+"from 3 well-known Cineplex in Singapore");
	
		System.out.println("\nIndicate yourself: ");
		System.out.println("1)user");
		System.out.println("2)Staff");
		choiceLogin = scan.nextInt();
		switch(choiceLogin){
		case 1:
			//Start User Application here
			
			break;
		case 2:
			StaffApplication.main(null);
			break;
		default:
			break;
		}
	}
}
