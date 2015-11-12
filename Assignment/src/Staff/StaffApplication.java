package Staff;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Cinema.Cinema;
import Cineplex.ChoiceException;
import Cineplex.Cineplex;
import Cineplex.CineplexDatabase;
import Movie.DateMovie;
import Movie.HolidayDataBase;
import Movie.Movie;
import Movie.RatedMovie;
import Movie.RatedMovieDatabase;

//Basically this class will enable staff to configure database of Cineplex from vendor(Cathay Cineplex for instance)
//and database of User

public class StaffApplication {
	
	private static Cineplex currentCineplex = null;
	private static Movie currentMovie = null;
	private static ArrayList<Cineplex> cineplexList;
	private static CineplexDatabase cDatabase;
	private static Scanner scan = new Scanner(System.in);
	private static RatedMovieDatabase ratedMovieDatabase = new RatedMovieDatabase();
	private static HolidayDataBase holidayDatabase = new HolidayDataBase();
	@SuppressWarnings("rawtypes")
	private static ArrayList listRatedMovie = ratedMovieDatabase.readFromDatabase("RatedMovie.dat");
	@SuppressWarnings({ "rawtypes" })
	private static ArrayList listHoliday = holidayDatabase.readFromDatabase("HolidayDatabase.dat");
	private static RatedMovie movieRated = (RatedMovie)listRatedMovie.get(0);
	
	
	public static void main(String args[]){
		//declare all the object 
		String username=null,password= null;
		Staff inputStaff= null;
		ArrayList<Staff> getList = null;	
		boolean staffStatus = false;
		DatabaseStaff dbStaff = new DatabaseStaff();
		
		//scan the staff username and password
		System.out.println("------------------------");
		System.out.print("Username: ");
		username = scan.nextLine();
		System.out.print("Password: ");
		password = scan.nextLine();
		System.out.println("------------------------");
		inputStaff = new Staff(username,password);
		//processing database
		System.out.println("Check Database");
		System.out.println("process.....\n");
		getList =  dbStaff.readFromDatabase("staff.dat");
		for(int i = 0 ; i < getList.size() ; i++){
			Staff printStaff = (Staff)getList.get(i);
			if(printStaff.EqualsStaff(inputStaff)){
				staffStatus = true;
				break;
			}
		}
		
		if(staffStatus){
			System.out.println("welcome Staff\n");
			viewCinemaFromDatabase();
		}else{
			System.out.println("Sorry your username and password is not match with our database");
		}
	}
	
	private static void viewCinemaFromDatabase(){
		cDatabase = new CineplexDatabase();
		cineplexList = new ArrayList<Cineplex>();
		cineplexList = cDatabase.readFromDatabase("CineplexDatabase.dat");
		System.out.println("------------------------");
		System.out.println("List of Cineplex from Cathay Cineplex");
		for(int i = 0 ;i < cineplexList.size() ; i++){	
			System.out.printf("%d) %s\n",i+1,cineplexList.get(i).getCineplexName());
		}
		System.out.println("------------------------");
		System.out.println("\n\nPlease choose one Cineplex from Cathay Cineplex to configure or type -1 to quit");
		try{
			int choices = scan.nextInt();
		if(choices > cineplexList.size()){
			System.out.println("Sorry your cineplex option is invalid");
			System.out.println("Please choose another option");
			viewCinemaFromDatabase();
			return;
		}
		if(choices == -1){
			System.out.println("Quit from the configuring process");
			return ;
		}
		
		currentCineplex = cineplexList.get(choices-1);
		try{
			configuring();
		}catch(ChoiceException e){
			System.out.println(e.getMessage());
			System.out.println("Please choose the below option");
			System.out.println("1) Quit");
			System.out.println("2) Configure again");
			int option = scan.nextInt();
			if(option == 1){
				System.out.println("Quitting process");
				return;
			}else{
				viewCinemaFromDatabase();
			}
		}catch(IndexOutOfBoundsException e){
			System.out.println("Your index choice is invalid ");
			e.printStackTrace();
			System.out.println("Please choose the below option");
			System.out.println("1) Quit");
			System.out.println("2) Configure again");
			int option = scan.nextInt();
			if(option == 1){
				System.out.println("Quitting process");
				return;
			}else{
				viewCinemaFromDatabase();
			}
		}catch(InputMismatchException e){
			System.out.println("\nYour input is invalid\n");
			System.out.println("Please choose the below option");
			System.out.println("1) Quit");
			System.out.println("2) Configure again");
			int option = scan.nextInt();
			if(option == 1){
				System.out.println("Quitting process");
				return;
			}else{
				viewCinemaFromDatabase();
			}
		}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("sorry system undergo problem");
			System.out.println("Please choose the below option");
			System.out.println("1) Quit");
			System.out.println("2) Configure again");
			scan.nextLine();
			int option = scan.nextInt();
			if(option == 1){
				System.out.println("Quitting process");
				return;
			}else{
				viewCinemaFromDatabase();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void configuring() throws ChoiceException {
		//get Movie from cineplex
		while(true){
			System.out.println("------------------------");
			System.out.println("Configuring "+currentCineplex.getCineplexName());
			System.out.println("1) Add or Remove or see Holiday from "+currentCineplex.getCineplexName());
			System.out.println("2) Update the movie from "+currentCineplex.getCineplexName());
			System.out.println("3) Add movie to the "+currentCineplex.getCineplexName());
			System.out.println("4) Remove movie from "+currentCineplex.getCineplexName());
			System.out.println("5) See List of Movie on "+currentCineplex.getCineplexName());
			System.out.println("6) Configure another cineplex");
			System.out.println("7) Quit");
			System.out.println("-------- ----------------");
			System.out.println("Please choose the option above");
			int OptionMovie = scan.nextInt();
			switch(OptionMovie){
				case 1:
					ConfigureHoliday();
					break;
				case 2:
					if(currentCineplex.getMovieList().size() > 0){
						updatingMovie();
					}else{
						System.out.println("Sorry there has no movie in "+currentCineplex.getCineplexName());
					}
					break;
				case 3:
					if(currentCineplex.getMovieList().size()> 0){
						addMovie();
					}else{
						System.out.println("Sorry there has no movie in "+currentCineplex.getCineplexName());
					}
					break;
				case 4:
					if(currentCineplex.getMovieList().size() > 0){
						removeMovie();
					}else{
						System.out.println("Sorry there has no movie in "+currentCineplex.getCineplexName());
					}
					break;
				case 5:
					getMovieList();
					break;
				case 6:
					viewCinemaFromDatabase();
					return;
				case 7:
					System.out.println("Please choose the option below");
					System.out.println("1) Quit to the cineplex configuration");
					System.out.println("2) Quit from system");
					int option = scan.nextInt();
					if(option == 1){
						viewCinemaFromDatabase();
					}else if(option == 2){
						System.out.println("Quitting from system");
					}
					return;
				default:
					throw new ChoiceException("Sorry your input is not listed in our system");
			}
			cDatabase.writeToDatabase("CineplexDatabase.dat",cineplexList);
			ratedMovieDatabase.writeToDatabase("RatedMovie.dat",listRatedMovie);
			holidayDatabase.writeToDatabase("HolidayDatabase.dat",listHoliday);
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void ConfigureHoliday(){
		System.out.println("\n------------------------");
		System.out.println("1) Add Holiday");
		System.out.println("2) Remove Holiday");
		System.out.println("3) See the holiday list");
		System.out.println("\n------------------------");
		System.out.println("Please choose the option above");
		int holidayOption = scan.nextInt();
		if(holidayOption == 1){
			System.out.println("please specify ");
			System.out.print("Year(yyyy): ");
			int holidayYear = scan.nextInt();
			System.out.print("Month(MM): ");
			int holidayMonth = scan.nextInt();
			System.out.print("Day(dd): ");
			int holidayDay = scan.nextInt();
			DateMovie holidayDate  = new DateMovie(holidayYear,holidayMonth,holidayDay);
			System.out.println("Holiday on "+holidayDate.getYearMonthDay()+" has been set");
			listHoliday.add(holidayDate);
		}else if(holidayOption == 2){
			System.out.println("Holiday schedule of "+currentCineplex.getCineplexName());
			int removeHoliday = getIndexHoliday();
			listHoliday.remove(removeHoliday-1);
			System.out.println("changes has been saved to the system\n");
		}else if(holidayOption == 3){
			System.out.println("Holiday schedule of "+currentCineplex.getCineplexName());
			for(int i = 0 ; i < listHoliday.size() ; i++){
				DateMovie holiday = (DateMovie)listHoliday.get(i);
				System.out.printf("%d) %s\n",i+1,holiday.getYearMonthDay());
			}
			System.out.println("\n");
		}else{
			System.out.println("Sorry your input is not listed in our system ");
			System.out.println("Please choose below option");
			System.out.println("1) Quit to "+currentCineplex.getCineplexName()+" configuration");
			System.out.println("2) Configure Holiday again");
			int option = scan.nextInt();
			if(option == 1){
				System.out.println("");
				return;
			}else{
				ConfigureHoliday();
			}
			return;
		}
	}
	
	private static void updatingMovie(){
		System.out.println("\n------------------------\n");
		System.out.println("List movie of "+currentCineplex.getCineplexName()+"............");
		for(int i = 0 ; i < currentCineplex.getMovieList().size() ; i++){
			System.out.println(i+1+")");
			System.out.printf("%s\n",currentCineplex.getMovieList().get(i).printDescription());
		}
		System.out.println("\n------------------------\n");
		System.out.println("\nPlease choose one movie to configure\n");
		int indexMovie = scan.nextInt();
		if(indexMovie > currentCineplex.getMovieList().size()){
			System.out.println("Sorry your movie option is invalid");
			System.out.println("Please choose again");
			updatingMovie();
			return;
		}
		currentMovie = currentCineplex.getMovie(indexMovie-1);
		
		if(currentCineplex.getMovieList().size() != 0 ){
			//later need to add the Date attribute so it will detect current time and the time when you put the object
			System.out.println("What description you want to configure");
			System.out.println("1) title");
			System.out.println("2) duration");
			System.out.println("3) ratingPG");
			System.out.println("4) Genre");
			System.out.println("5) price");
			System.out.println("6) Director");
			System.out.println("7) Cast");
			System.out.println("8) Synopsis");
			System.out.println("9) date and status");
			
			
			int MovieConfigurationDescription = scan.nextInt();
			System.out.flush();
			switch(MovieConfigurationDescription){
				case 1:
					System.out.println("insert the new title");
					scan.nextLine();
					String newTitle = scan.nextLine();
					currentMovie.setTitle(newTitle);
					break;
				case 2:
					System.out.println("insert the new duration in minute");
					scan.nextLine();
					String newDuration = scan.nextLine();
					currentMovie.setDuration(newDuration);
					break;
				case 3:
					System.out.println("Set the new RatingPG");
					scan.nextLine();
					String newRatingPG = scan.nextLine();
					currentMovie.setRatingPG(newRatingPG);
					break;
				case 4:
					System.out.println("Set the new Genre of the movie");
					scan.nextLine();
					String newGenre = chooseMovieType();
					currentMovie.setGenre(newGenre);
					break;
				case 5:
					System.out.println("insert the new price of the movie in SGD");
					scan.nextLine();
					double price = scan.nextDouble();
					currentMovie.setPrice(price);
					break;
				case 6:
					System.out.println("insert the new Director of the movie");
					scan.nextLine();
					String director = scan.nextLine();
					currentMovie.setDirector(director);
					break;
				case 7:
					ArrayList<String> listCasting = currentMovie.getCastList();
					System.out.println("List of  cast: ");
					for(int i = 0 ; i < listCasting.size() ; i++){
						System.out.printf("%d) %s\n",i+1,listCasting.get(i));
					}
					System.out.println("");
					System.out.println("Editting the cast");
					System.out.println("1) add new cast");
					System.out.println("2) Remove cast");
					System.out.println("3) Update new  List of cast");
					
					int optionCast = scan.nextInt();
					if(optionCast == 1){
						System.out.println("insert the new cast ");
						scan.nextLine();
						String newCast = scan.nextLine();
						listCasting.add(newCast);
					}else if(optionCast == 2){
						System.out.println("List of cast");
						//need to exception
						int deleteCast = getIndexCast(listCasting);
						listCasting.remove(deleteCast-1);
					}else if(optionCast  == 3){
						System.out.println("How many cast you want to add");
						int castNumber = scan.nextInt();
						while(castNumber < 2){
							System.out.println("please choose at least 2");
							System.out.println("How many cast you want to add(at least 2)");
							castNumber = scan.nextInt();
						}
						ArrayList<String> castList = new ArrayList<String>(); 
						scan.nextLine();
						for(int i = 0 ; i < castNumber ; i++){
							System.out.printf("name of cast %d: ",i+1);
							String cast = scan.nextLine();
							castList.add(cast);
						}
						listCasting = castList;
					}else{
						System.out.println("Sorry your option is not listed in our system");
					}
					currentMovie.setCastList(listCasting);
					break;
				case 8:
					System.out.println("insert the new Synopsis");
					scan.nextLine();
					String synopsis = scan.nextLine();
					currentMovie.setSynopsis(synopsis);
					break;	
				case 9:
					configuringDate();
					break;
				default:
					System.out.println("Sorry your choice is not listed in our system");
					System.out.println("Please choose the below option");
					System.out.println("1) Quit to "+currentCineplex.getCineplexName()+" configuration");
					System.out.println("2) Editing Movie again");
					int option = scan.nextInt();
					if(option == 1){
						return;
					}else if(option == 2){
						System.out.println("");
						updatingMovie();
						return;
					}
					
			}	
			System.out.println("\nchanges has been saved to our system\n");
		}else{
			System.out.println("\nDont have any movie on the list, please add at least one of the movie to the cinema");
		}
	}
	
	private static void addMovie(){
		//get the description of the movie from user
		String title,duration,genre,ratingPG;
		DateMovie dateMovie;
		double price;
		scan.nextLine();
		System.out.println("insert the title of new movie");
		title = scan.nextLine();
		System.out.println("insert the duration of new movie, duration in minute");
		duration = scan.nextLine();
		System.out.println("insert the genre of the new movie");
		genre = chooseMovieType();
		scan.nextLine();
		System.out.println("insert the RatingPG");
		ratingPG = scan.nextLine();
		System.out.println("insert the price of the movie in SGD");
		price = scan.nextDouble();
		System.out.println("insert the director");
		scan.nextLine();
		String director = scan.nextLine();
		System.out.println("How many cast you want to add(at least 2)");
		int castNumber = scan.nextInt();
		while(castNumber < 2){
			System.out.println("please choose at least 2");
			System.out.println("How many cast you want to add(at least 2)");
			castNumber = scan.nextInt();
		}
		ArrayList<String> castList = new ArrayList<String>(); 
		scan.nextLine();
		for(int i = 0 ; i < castNumber ; i++){
			System.out.printf("name of cast %d: ",i+1);
			String cast = scan.nextLine();
			castList.add(cast);
		}
		System.out.println("\ninsert the synopsis");
		String synopsis = scan.nextLine();
		dateMovie = addDateOnNewMovie(title);
		Movie movie = new Movie(title, duration, ratingPG, genre, dateMovie, price, director, castList, synopsis);
		if(!dateMovie.getStatus().equals("Coming Soon")){
			movieRated.addMovieList(movie);
		}
		currentCineplex.addMovie(movie);
		System.out.println("Changes has been saved to our system\n");
	
	}
	
	public static void removeMovie(){
		System.out.println("\n------------------------\n");
		System.out.println("List movie of "+currentCineplex.getCineplexName()+"............");
		for(int i = 0 ; i < currentCineplex.getMovieList().size() ; i++){
			System.out.println(i+1+")");
			System.out.printf("%s\n",currentCineplex.getMovieList().get(i).printDescription());
		}
		System.out.println("\n------------------------\n");
		
		System.out.println("which movie to be removed");
		int optionRemoveMovie = scan.nextInt();
		boolean pass = true;
		String movieDeleted = currentCineplex.getMovieList().get(optionRemoveMovie-1).getTitle();
		currentCineplex.removeMovie(currentCineplex.getMovieList().get(optionRemoveMovie-1));
		//need to remove the movie from rated movie if there has no such movie again in all the cineplex
		for(int i = 0 ; i < cineplexList.size() ; i++){
			Cineplex cineplex = cineplexList.get(i);
			ArrayList<Movie> movieRemovedList = cineplex.getMovieList();
			for(int j = 0 ; j < movieRemovedList.size() ;j++){
				if(movieRemovedList.get(j).getTitle().equals(movieDeleted)){
					//means still has this movie in other cineplex so cannot delete it
					pass = false;
					break;
				}
			}
		}
		if(pass){
			movieRated.removeMovie(movieDeleted);
		}
		System.out.println("changes has been saved to our system \n");
	}
	
	private static void getMovieList(){
		for(int i = 0 ; i < currentCineplex.getMovieList().size() ; i++){
			System.out.println("\n****************************************\n");
			System.out.println(currentCineplex.getMovieList().get(i).printDescription());
		}
	}
	
	private static void configuringDate(){
		//getting the list of date on the currentMovie
		System.out.flush();
		System.out.println("Please choose one option");
		System.out.println("1) Add another date");
		System.out.println("2) Editing the existing date");
		System.out.println("3) Remove the date");
		int addDate = scan.nextInt();
		if(addDate == 1){
			setNewDateOfMovie(currentMovie);
		}else if(addDate == 2){
			editStatusTimeCinema();
		}else if(addDate == 3){
			removeTheDate();
		}else{
			System.out.println("Sorry your choice is not listed in our system");
			System.out.println("Please choose the below option");
			System.out.println("1) Quit to "+currentMovie.getTitle()+" configuration");
			System.out.println("2) Editting Date again");
			int option = scan.nextInt();
			if(option == 1){
				return;
			}else if(option == 2){
				System.out.println("");
				configuringDate();
				return;
			}
		}
	}
	
	public static void removeTheDate(){
		System.out.println("Schedule "+currentMovie.getTitle()+" :");
		currentMovie.getListDateStatusMovie();
		System.out.println("\n");
		ArrayList<DateMovie> listMovie = currentMovie.getArrayListOfDateMovie();
		System.out.println("Please choose which date you want to configure");
		int indexListMovie = scan.nextInt();
		DateMovie currentDate = listMovie.get(indexListMovie-1);
		listMovie.remove(currentDate);
	}
	
		
	private static void editStatusTimeCinema(){
		System.out.println("Schedule "+currentMovie.getTitle()+" :");
		currentMovie.getListDateStatusMovie();
		System.out.println("\n");
		ArrayList<DateMovie> listMovie = currentMovie.getArrayListOfDateMovie();
		System.out.println("Please choose which date you want to configure");
		int indexListMovie = scan.nextInt();
		DateMovie currentDate = listMovie.get(indexListMovie-1);
		System.out.println("\nPlease choose which one you want to edit on "+currentDate.getStatusTimeMovie());
		System.out.println("1) Time");
		System.out.println("2) status");
		System.out.println("3) cinema");
		int timestatus = scan.nextInt();
		if(timestatus == 1){
			editTime(listMovie.get(indexListMovie-1));
		}else if(timestatus == 2){
			System.out.println("insert the new Status");
			scan.nextLine();
			String newStatus = chooseStatusMovie();
			currentDate.setStatus(newStatus);
			if(!newStatus.equals("Coming Soon")){
				movieRated.addMovieList(currentMovie);
			}
		}else if(timestatus == 3){
			System.out.println("List Cinema: ");
			int choosenCinema = getIndexCinema();
			currentDate.setCinema(currentCineplex.getCinema(choosenCinema-1));
		}
		else{
			System.out.println("Sorry your choice is not listed in the system");
			System.out.println("Please choose the below option");
			System.out.println("1) Quit to Date configuration");
			System.out.println("2) Editing Date attribute again");
			int option = scan.nextInt();
			if(option == 1){
				return;
			}else if(option == 2){
				System.out.println("");
				editStatusTimeCinema();
				return;
			}
			return ;
		}
	}
	
	private static DateMovie addDateOnNewMovie(String title){
		int year,month,day,hour,minute;
		DateMovie dateMovie;
		String status;
		System.out.println("insert the date: ");
		System.out.print("Year(YYYY): ");
		year = scan.nextInt();
		System.out.print("Month(MM): ");
		month = scan.nextInt();
		System.out.print("Day(DD): ");
		day = scan.nextInt();
		System.out.print("Hour(hh): ");
		hour = scan.nextInt();
		System.out.print("Minute(mm): ");
		minute = scan.nextInt();
		scan.nextLine();
		DateMovie checkHolidayDate = new DateMovie(year,month,day);
		System.out.println("insert the status of the movie on that time");
		status = chooseStatusMovie();
		System.out.println("insert the cinema that will be playing "+title+" on "+checkHolidayDate.getYearMonthDay());
		int cinemaoption = getIndexCinema();
		Cinema cinema = currentCineplex.getCinema(cinemaoption-1);
		dateMovie = new DateMovie(cinema,status,year, month, day, hour, minute);
		return dateMovie;
	}

	private static void setNewDateOfMovie(Movie chooseMovie) {
		System.out.println("insert the new date with format");
		System.out.print("YYYY: ");
		int year = scan.nextInt();
		System.out.print("MM: ");
		int month = scan.nextInt();
		System.out.print("DD: ");
		int day = scan.nextInt();
		System.out.print("HH: ");
		int hour = scan.nextInt();
		System.out.print("mm: ");
		int minute = scan.nextInt();
		DateMovie datemovie = new DateMovie(year, month, day, hour, minute);
		System.out.println("insert status to this new Date "+datemovie.getTime());
		scan.nextLine();
		String newStatus = chooseStatusMovie();
		System.out.println("specify which cinema that will play on "+datemovie.getYearMonthDay());
		int cinemaoption = getIndexCinema();
		Cinema cinema = currentCineplex.getCinema(cinemaoption-1);
		datemovie.setStatus(newStatus);
		datemovie.setCinema(cinema);
		currentMovie.setTimeStatus(datemovie);
		System.out.println("\nYour change has been saved to our system\n");
	}
	
	private static void editTime(DateMovie currentDate){
			
			System.out.println("Please choose which part of date you want to edit");
			System.out.println("1) Year");
			System.out.println("2) Month");
			System.out.println("3) Day");
			System.out.println("4) Hour");
			System.out.println("5) Minute");
			int DateEditPart = scan.nextInt();
			switch(DateEditPart){
			case 1:
				System.out.printf("year(yyyy): ");
				int newYear = scan.nextInt();
				currentDate.setYear(newYear);
				
				break;
			case 2:
				System.out.println("Month(MM): ");
				int newMonth = scan.nextInt();
				currentDate.setMinute(newMonth);
				break;
			case 3:
				System.out.printf("Day(dd): ");
				int newDay = scan.nextInt();
				currentDate.setDay(newDay);
				break;
			case 4:
				System.out.printf("Hour(hh): ");
				int newHour = scan.nextInt();
				currentDate.setHour(newHour);
				break;
			case 5:
				System.out.printf("Minute(mm): ");
				int newMinute = scan.nextInt();
				currentDate.setMinute(newMinute);
				break;
			
			}
			
		}
	
	private static int getIndexCinema(){
		for(int i = 0 ; i < currentCineplex.getCinemaList().size() ; i++){
			System.out.printf("%d) %s\n",i+1,currentCineplex.getCinema(i).getCinemaName());
		}
		System.out.println("please choose which cinema ");
		int choosenCinema = scan.nextInt();
		if(choosenCinema >  currentCineplex.getCinemaList().size()){
			System.out.println("Sorry your cinema option is invalid");
			System.out.println("Please choose again");
			return getIndexCinema();
		}
		return choosenCinema;
		
	}
	
	private static int getIndexCast(ArrayList<String> listCasting){
		for(int i = 0 ; i < listCasting.size() ; i++){
			System.out.printf("%d) %s\n",i+1,listCasting.get(i));
		}
		System.out.println("Which one you wish to delete");
		int deleteCast = scan.nextInt();
		if(deleteCast > listCasting.size()){
			System.out.println("Sorry your cast option is invalid");
			System.out.println("Please input again");
			return getIndexCast(listCasting);
		}
		return deleteCast;
	}
	
	private static int getIndexHoliday(){
		for(int i = 0 ; i < listHoliday.size() ; i++){
			DateMovie holiday = (DateMovie)listHoliday.get(i);
			System.out.printf("%d) %s\n",i+1,holiday.getYearMonthDay());
		}
		System.out.println("\nwhich holiday date you want to remove\n");
		int removeHoliday = scan.nextInt();
		if(removeHoliday > listHoliday.size()){
			System.out.println("Sorry your holiday option is invalid");
			System.out.println("Please choose again");
			return getIndexHoliday();
		}
		return removeHoliday;
	}
	
	
	public static String chooseStatusMovie(){
		System.out.println("1) Coming soon");
		System.out.println("2) Preview");
		System.out.println("3) Now Showing");
		System.out.println("4) End of Showing");
		System.out.println("please choose the above option of status movie");
		int statusMovie = scan.nextInt();
		String returnString = null;
		switch(statusMovie){
		case 1:
			returnString = "Coming Soon";
			break;
		case 2:
			returnString = "Preview";
			break;
		case 3:
			returnString = "Now Showing";
			break;
		case 4:
			returnString = "End of Showing";
			break;
		}
		return returnString;
	}
	
	
	public static String chooseMovieType(){
		System.out.println("1) Action");
		System.out.println("2) Drama");
		System.out.println("3) Comedy");
		System.out.println("4) Romantic Comedy");
		System.out.println("5) Horror");
		System.out.println("6) BlockBuster");
		System.out.println("7) 3D");
		System.out.println("8) define yourself");
		int optionTypeMovie = scan.nextInt();
		String returnString = null;
		switch(optionTypeMovie){
			case 1:
				returnString  = "Action";
				break;			
			case 2:
				returnString = "Drame";
				break;
			case 3:
				returnString = "Comedy";
				break;
			case 4:
				returnString = "Romantic Comedy";
				break;
			case 5:
				returnString = "Horror";
				break;
			case 6:
				returnString = "BlockBuster";
				break;
			case 7:
				returnString = "3D";
				break;
			case 8:
				scan.nextLine();
				System.out.println("please insert the type of movie here");
				returnString = scan.nextLine();
				break;
			
		}
		return returnString;
	}
	

}
