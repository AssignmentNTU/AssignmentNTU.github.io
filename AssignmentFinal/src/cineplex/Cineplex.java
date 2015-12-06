package cineplex;

import java.io.Serializable;
import java.util.ArrayList;

import cinema.Cinema;
import movie.MovieDate;
import movie.Movie;

//Each of cineplex will have a list of cinema

/**
	Represents a Cineplex.
	Each Cineplex has many Cinemas, many Movies.
	@author Edward Sujono
	@version 1.0
	@since 2015-11-01
*/

public class Cineplex implements Serializable{
	/**
	 * The name of this Cineplex.
	 */
	private String cineplexName="";
	/**
	 * The number of Cinema in this Cineplex.
	 */
	private int cinemaSize = 0;
	/**
	 * The list of Cinema in this Cineplex.
	 */
	private ArrayList<Cinema> cinemaList = new ArrayList<Cinema>();
	/**
	 * The list of Movie in this Cineplex.
	 */
	private ArrayList<Movie> movieList = new ArrayList<Movie>();

	//we have 2 option here using 1 more movie list again or 
	//constructor
	/**
	 * Creates a new Cineplex.
	 */
	public Cineplex(){
		
	}
	/**
	 * Creates a new Cineplex with given name.
	 * @param cineplexName The name of cineplex.
	 */
	public Cineplex(String cineplexName){
		this.cineplexName = cineplexName;
	}
	
	//end of constructor
	

	
	//add and remove  movie 
	/**
	 * Add movie to the movieList.
	 * @param movie The movie to be added.
	 */
	public void addMovie(Movie movie){
		movieList.add(movie);
	}
	/**
	 * Gets this Cineplex's movieList.
	 * @return this Cineplex's movieList.
	 */
	public ArrayList<Movie> getMovieList(){
		return movieList;
	}
	/**
	 * Gets particular Movie in the movieList with index.
	 * @param index Index of the movieList
	 * @return The particular Movie in the movieList in specified index.
	 */
	public Movie getMovie(int index){
		return movieList.get(index);
	}
	/**
	 * Remove movie to the movieList.
	 * @param movie The movie to be removed.
	 */
	public void removeMovie(Movie movie){
		movieList.remove(movie);
	}
	//end of add and remove movie
	
	/**
	 * Gets this Cineplex's name.
	 * @return this Cineplex's name.
	 */
	
	public String getCineplexName(){
		return cineplexName;
	}
	/**
	 * Gets this Cineplex's cinemaList.
	 * @return this Cineplex's cinemaList.
	 */
	public ArrayList<Cinema> getCinemaList(){
		return cinemaList;
	}
	
	/**
	 * Add Cinema to the cinemaList.
	 * @param cinema The Cinema to be added.
	 */
	public void addCinema(Cinema cinema){
		cinemaList.add(cinema);
	}
	/**
	 * Gets particular Cinema in the cinemaList with index.
	 * @param index Index of the cinemaList.
	 * @return The particular Cinema in the cinemaList in specified index.
	 */
	public Cinema getCinema(int index){
		return cinemaList.get(index);
	}
	
	/**
	 * Remove cinema to the cinemaList.
	 * @param movie The cinema to be removed.
	 */
	public void RemoveCinema(Cinema cinema){
		for(int i = 0 ; i < cinemaList.size() ; i++){
			if(cinemaList.get(i).equals(cinema)) {
				cinemaList.remove(i);
				break;
			}
		}
	}
	/**
	 * Print all the movies in all cinema.
	 * Status of the movie will be shown.
	 * @param bufferMoviePreview A list of movie to be printed.
	 */
	public static void printMovieForUser(ArrayList<Movie> bufferMoviePreview){
		for(int i = 0 ; i < bufferMoviePreview.size()  ;i++){
			System.out.println("---------------------------------------------------");
			System.out.println(i+1+")");
			System.out.println(bufferMoviePreview.get(i).printMovieListingUser());
			System.out.print("\nStatus of Movie: ");
			String status = bufferMoviePreview.get(i).getArrayListOfMovieDate().get(0).getStatus();
			if(status.equals("End of Showing")){
				status = "Now Showing";
			}
			System.out.println(status);	
			System.out.println("---------------------------------------------------");
		}
	}
	/**
	 * Print all the movies.
	 * Status of the movie will not be shown.
	 */
	
	public void listMovie(){
		
		if(movieList.size() == 0) return;
		{
			
			for (int i = 0; i < movieList.size(); i++){
				
				System.out.printf("%d) ", i + 1);
				System.out.println(movieList.get(i).getTitle());
				
			}
		}	
	}
	
	/**
	 * Get all the movie available.
	 * Only now showing movie and preview movie will be listed.
	 * @return the list of movies that are available.
	 */
	public ArrayList<Movie> getMovieListAvailable(){
		ArrayList<Movie> availableMovie = new ArrayList<Movie>();
		
		for(int i = 0 ; i< movieList.size() ; i++){
			if(!movieList.get(i).getArrayListOfMovieDate().get(0).getStatus().equals("Coming soon")){
				availableMovie.add(movieList.get(i));
			}
		}
		
		return availableMovie;
	}
	
	
}