package database;

import java.util.ArrayList;
import java.util.Scanner;

import cinema.Cinema;
import cineplex.Cineplex;
import cineplex.CineplexDatabase;
import movie.HolidayDatabase;
import movie.Movie;
import movie.MovieDate;
import movie.RatedMovie;
import movie.RatedMovieDatabase;
import staff.StaffDatabase;
import staff.Staff;
import user.CustomerDatabase;

public class DatabaseReset {
	
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.println("Start restart all database");
		System.out.println("1) Restart Cineplex Database");
		System.out.println("2) Restart Rated Movie Database");
		System.out.println("3) Restart Holiday Database");
		System.out.println("4) Restart customerDatabase");
		System.out.println("5) Restart staff Database");
		System.out.println("6) Restart all");
		int opt = scan.nextInt();
		switch(opt){
			case 1:
				restartCineplexDatabase();
				break;
			case 2:
				restartRatedMovieDatabase();
				break;
			case 3:
				restartHolidayDatabase();
				break;
			case 4:
				restartCustomerDatabase();
				break;
			case 5:
				restartStaffDatabase();
				break;
			case 6:
				restartCineplexDatabase();
				System.out.println("---------");
				restartRatedMovieDatabase();
				System.out.println("---------");
				restartHolidayDatabase();
				System.out.println("---------");
				restartCustomerDatabase();
				System.out.println("---------");
				restartStaffDatabase();
				break;
			default:
				System.out.println("Your choice is invalid");
		}
	}
	
	private void witeDatabase(Database database,String fileName, ArrayList list){
		database.writeToDatabase(fileName, list);
	}
	
	
	private static void restartCineplexDatabase(){
		System.out.println("Start constructing database for Cineplex");
		//Change code in here to configure the database 
		//automatically configure 3 Cineplex from 1 company which is Cathat 
		ArrayList<Cineplex> listCineplex = new ArrayList<Cineplex>();
		
		//in this case cathay cineplex will be acts as vendor
		//inside cathay cineplex there will be several cineplex (3 or more in this case)
		//inside several cineplex there will have several cinema which is room theater 
		//so inside the room theater there must be have list of movie that will be shown there
		
		//in the first case we make 3 cineplex 
		Cineplex AMKHub = new Cineplex("AMKHub");
		Cineplex Jem    = new Cineplex("JEM");
		Cineplex WestMall = new Cineplex("West Mall");
		/********************************************AMKHub******************************************************/
		//each cineplex there has 3 cinema 
		Cinema amkHubFirst = new Cinema("Room 1","Reguler");
		Cinema amkHubSecond = new Cinema("Room 2","Reguler");
		Cinema amkHubThird = new Cinema("Room 3","Platinum");
		
		AMKHub.addCinema(amkHubFirst);
		AMKHub.addCinema(amkHubSecond);
		AMKHub.addCinema(amkHubThird);
		
		MovieDate walkDate = new MovieDate(amkHubFirst,"Now Showing",2015,12,10,12,20);
		ArrayList<String> castList = new ArrayList<String>();
		castList.add("Joseph Gordon-Levitt");
		castList.add("Ben Kingsley");
		Movie theWalk = new Movie("The walk","123","PG some intense sequence","Action",walkDate,10,"Robert Zemeckis",castList,
				"Twelve people have walked on the moon, but only one man has ever, or will ever, walk in the immense void between the World Trade Center towers."
				+ " Guided by his real-life mentor, Papa Rudy (Ben Kingsley), and aided by an unlikely band of international recruits,"
				+ " Petit and his gang overcome long odds, betrayals, dissension and countless close calls to conceive and execute their mad plan.");
		
		AMKHub.addMovie(theWalk);
		
		
		
		
		/*****************************************JEM**************************************************************/
		Cinema firstCinemaJem = new Cinema("Room1","Reguler");
		Cinema secondCinemaJem = new Cinema("Room2","Platinum");
		Cinema thirdCinemaJem = new Cinema("Room3","Reguler");
				 
		Jem.addCinema(firstCinemaJem);
		Jem.addCinema(secondCinemaJem);
		Jem.addCinema(thirdCinemaJem);
		
		MovieDate LastWitchHunterDate = new MovieDate(firstCinemaJem,"Coming soon",2015,10,10,13,20);
		ArrayList<String> castList1 = new ArrayList<String>();
		castList.add("Vin Diesel");
		castList.add("Elijah Wood");
		Movie LastWitchHunter = new Movie("Last Witch Hunter","107","PG 13-some violence","Action",LastWitchHunterDate,10,"Breck Eisner",castList1,
				"Five hundred years ago, war raged between humankind and witches, vicious creatures that unleashed a Black Death upon the world."
				+ " Armies of witch hunters followed their leader, the great and mighty KAULDER, into a final battle that would bring the war to a close."
				+ " In a rage, Kaulder managed to kill the all-powerful QUEEN WITCH and capture the mysterious source of her power, the Plague Box. "
				+"However, in the moments before her death, the Queen cursed Kaulder with immortality");
		
		
		
		
		Jem.addMovie(LastWitchHunter);
		

		
		
		//implementation of the movie list will be added later by StaffApplication
		
		/*****************************************WestMall********************************************************/
		
		Cinema firstWestMall = new Cinema("Room1");
		Cinema secondWestMall = new Cinema("Room2");
		Cinema thirdWestMall = new Cinema("Room3");
		
		WestMall.addCinema(firstWestMall);
		WestMall.addCinema(secondWestMall);
		WestMall.addCinema(thirdWestMall);
		
		
		
		//populate more movie into the database
		
		/**********************first movie populate in all cineplex***********************/
		MovieDate paranomalActivityDate = new MovieDate(secondCinemaJem,"Coming soon",2015,12,12,15,30);
		MovieDate paranomalActivityDate1 = new MovieDate(firstWestMall,"Coming soon",2015,12,12,15,30);
		MovieDate paranomalActivityDate2 = new MovieDate(amkHubFirst,"Coming soon",2015,12,12,15,30);

		ArrayList<String> castparanomalAct  = new ArrayList<String>();
		castparanomalAct.add("Chris J. Murray");
		castparanomalAct.add("Brit Shaw");
		
		Movie paranomalActivity = new Movie("Paranormal Activity The Ghost Dimension","95","NC16 - Horror and coarse language","Horror"
		,paranomalActivityDate,10,"Gregory Plotkin",castparanomalAct,"The Ghost Dimension, follows a new family, The Fleeges - father Ryan (Chris J. Murray), mother Emily (Brit Shaw) and their young daughter Leila"
				+ " (Ivy George) - Who move into a house and discover a video camera and a box of tapes in the garage. When "
				+ "they look through the camera's lens, they begin to see the paranormal activity happening around them - including the re-emergence "
				+ "of young Kristi and Katie.");
		
		
		Movie paranomalActivity1 = new Movie("Paranormal Activity The Ghost Dimension","95","NC16 - Horror and coarse language","Horror"
				,paranomalActivityDate1,10,"Gregory Plotkin",castparanomalAct,"The Ghost Dimension, follows a new family, The Fleeges - father Ryan (Chris J. Murray), mother Emily (Brit Shaw) and their young daughter Leila"
						+ " (Ivy George) - Who move into a house and discover a video camera and a box of tapes in the garage. When "
						+ "they look through the camera's lens, they begin to see the paranormal activity happening around them - including the re-emergence "
						+ "of young Kristi and Katie.");
				
				
				
		Movie paranomalActivity2 = new Movie("Paranormal Activity The Ghost Dimension","95","NC16 - Horror and coarse language","Horror"
				,paranomalActivityDate2,10,"Gregory Plotkin",castparanomalAct,"The Ghost Dimension, follows a new family, The Fleeges - father Ryan (Chris J. Murray), mother Emily (Brit Shaw) and their young daughter Leila"
						+ " (Ivy George) - Who move into a house and discover a video camera and a box of tapes in the garage. When "
						+ "they look through the camera's lens, they begin to see the paranormal activity happening around them - including the re-emergence "
						+ "of young Kristi and Katie.");
				
				
		AMKHub.addMovie(paranomalActivity);
		Jem.addMovie(paranomalActivity1);
		WestMall.addMovie(paranomalActivity2);
		
		/**********************second movie populate in all cineplex***********************/
		
		MovieDate gooseBumpsDate = new MovieDate(thirdCinemaJem,"Coming soon",2015,12,12,15,30);
		MovieDate gooseBumpsDate1 = new MovieDate(secondWestMall,"Coming soon",2015,12,12,15,30);
		MovieDate gooseBumpsDate2 = new MovieDate(amkHubThird,"Coming soon",2015,12,12,15,30);

		ArrayList<String> castGooseBumps = new ArrayList<String>();
		castGooseBumps.add("Jack Black");
		castGooseBumps.add("Dylan Minnette Minnette");
		
		Movie gooseBumps = new Movie("Goosebumps","103","PG - Frightening scenes","BlockBuster"
		,gooseBumpsDate,10,"Rob Letterman",castGooseBumps,"Upset about moving from a big city to a small town, teenager Zach Cooper (Dylan Minnette) "
				+ "finds a silver lining when he meets the beautiful girl, Hannah (Odeya Rush), living right next door. But every silver lining has a cloud, "
				+ "and Zachs comes when he learns that Hannah has a mysterious dad who is revealed to be R. L. Stine (Jack Black), the author of the bestselling "
				+ "Goosebumps series. It turns out that there is a reason why Stine is so strange he is a prisoner of his own imagination");
		
		
		Movie gooseBumps1 = new Movie("Goosebumps","103","PG - Frightening scenes","BlockBuster"
		,gooseBumpsDate1,10,"Rob Letterman",castGooseBumps,"Upset about moving from a big city to a small town, teenager Zach Cooper (Dylan Minnette) "
				+ "finds a silver lining when he meets the beautiful girl, Hannah (Odeya Rush), living right next door. But every silver lining has a cloud, "
				+ "and Zachs comes when he learns that Hannah has a mysterious dad who is revealed to be R. L. Stine (Jack Black), the author of the bestselling "
				+ "Goosebumps series. It turns out that there is a reason why Stine is so strange he is a prisoner of his own imagination");
				
				
		Movie gooseBumps2 = new Movie("Goosebumps","103","PG - Frightening scenes","BlockBuster"
		,gooseBumpsDate2,10,"Rob Letterman",castGooseBumps,"Upset about moving from a big city to a small town, teenager Zach Cooper (Dylan Minnette) "
				+ "finds a silver lining when he meets the beautiful girl, Hannah (Odeya Rush), living right next door. But every silver lining has a cloud, "
				+ "and Zachs comes when he learns that Hannah has a mysterious dad who is revealed to be R. L. Stine (Jack Black), the author of the bestselling "
				+ "Goosebumps series. It turns out that there is a reason why Stine is so strange he is a prisoner of his own imagination");
		
		
		
		AMKHub.addMovie(gooseBumps);
		Jem.addMovie(gooseBumps1);
		WestMall.addMovie(gooseBumps2);
		
		
		
		//implementation of the movie list will be added later by Staff Application
		
		listCineplex.add(AMKHub);
		listCineplex.add(Jem);
		listCineplex.add(WestMall);
				
		CineplexDatabase cDatabase =  new CineplexDatabase();
		System.out.println("Finish Construct the original Database for Cineplex");
		//cDatabase.writeToDatabase("CineplexDatabase.dat", listCineplex);
		//CineplexDatabase cDatabase1 =  new CineplexDatabase();
		//ArrayList<Cineplex> listCineplex1 = cDatabase1.readFromDatabase("CineplexDatabase.dat");
		DatabaseReset all = new DatabaseReset();
		all.witeDatabase(cDatabase,"CineplexDatabase.dat" , listCineplex);
	}
	
	private static void restartRatedMovieDatabase(){
		System.out.println("Start Constructing the original database for Rated Movie");
		RatedMovieDatabase database = new RatedMovieDatabase();
		String fileName = "RatedMovie.dat";
		ArrayList listMovie = new ArrayList<RatedMovie>();
		//example for movie rating 
		Movie first = new Movie("The walk");
		//Movie second= new Movie("Last Witch Hunter");
		//Movie third = new Movie("Paranormal Activity The Ghost Dimension");
		//Movie fourth = new Movie("Goosebumps");
		RatedMovie ratedmovie = new RatedMovie();
		ratedmovie.addMovieList(first);
		//ratedmovie.addMovieList(second);
		//ratedmovie.addMovieList(third);
		//ratedmovie.addMovieList(fourth);
		listMovie.add(ratedmovie);
		database.writeToDatabase(fileName, listMovie);
		System.out.println("Finish construction the original database for Rated Movie");
		DatabaseReset all = new DatabaseReset();
		all.witeDatabase(database,fileName , listMovie);
	}
	
	private static void restartHolidayDatabase(){
		System.out.println("Start constructing the original database for holiday date");
		HolidayDatabase holidayDatabase = new HolidayDatabase();
		MovieDate holiday1 = new MovieDate(2012,10,10);
		MovieDate holiday2 = new MovieDate(2014,10,10);
		ArrayList dateList = new ArrayList<MovieDate>();
		dateList.add(holiday1);
		dateList.add(holiday2);
		String fileName = "HolidayDatabase.dat";
		holidayDatabase.writeToDatabase(fileName,dateList);
		System.out.println("Finish constructing the original database for holiday date");
		DatabaseReset all = new DatabaseReset();
		all.witeDatabase(holidayDatabase,fileName , dateList);
	}
	
	private static void restartCustomerDatabase(){
		System.out.println("Start constructing database for customer");
		CustomerDatabase customerDatabase = new CustomerDatabase();
		String filename = "CustomerDatabase.dat";
		ArrayList customerList = new ArrayList<>();
		customerDatabase.writeToDatabase(filename,customerList);
		System.out.println("Finish constructing database for Customer");
		DatabaseReset all = new DatabaseReset();
		all.witeDatabase(customerDatabase,filename,customerList);
	}
	
	
	private static void restartStaffDatabase(){
		System.out.println("Start constructing original database for Staff");
		ArrayList list = new ArrayList<Staff>();
		Staff newStaff = new Staff("EdwardSujono","12345");
		Staff newStaff1 = new Staff("chee","asdf");
		list.add(newStaff);
		list.add(newStaff1);
		String filename = "staff.dat";
		StaffDatabase dbs = new StaffDatabase();
		dbs.writeToDatabase(filename, list);
		System.out.println("Finish constructing original database for Staff");
		DatabaseReset all = new DatabaseReset();
		all.witeDatabase(dbs,filename,list);
	}
	
}
