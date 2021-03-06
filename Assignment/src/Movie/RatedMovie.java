package Movie;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class RatedMovie implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String movieTitle;	
	private double rating;
	private ArrayList<Movie> listMovieRated = new ArrayList<Movie>();
	
	//getter and setter for the listMovieRated
	public void addMovieList(Movie movie){
		//checking whether the movie has been in the list or not
		for(int i = 0 ; i < listMovieRated.size() ; i++){
			if(listMovieRated.get(i).getTitle().equals(movie.getTitle())){
				return;
			}
		}
		listMovieRated.add(movie);
	}
	
	public ArrayList<Movie> getRatedMovie(){
		return listMovieRated;
	}
	
	public void removeMovie(String nameMovie){
		for(int i = 0 ; i < listMovieRated.size() ;i++){
			if(listMovieRated.get(i).getTitle().equals(nameMovie)){
				listMovieRated.remove(i);
				break;
			}
		}
	}
	

	
	public Movie getMovieWithIndex(int index){
		return listMovieRated.get(index);
	}
	
	public void updateMovieRating(int index,double rating){
		Movie movie = listMovieRated.get(index);
		movie.setRating(rating);
		//System.out.println("test rating: "+listMovieRated.get(index).getRating());
	}
	
	public void updateMovieTicket(String movieName){
		Movie bufferMovie = null;
		for(int i = 0 ; i < listMovieRated.size() ; i++){
			if(listMovieRated.get(i).getTitle().equals(movieName)){
				bufferMovie = listMovieRated.get(i);
				System.out.println("successFull");
				bufferMovie.increaseTicket();
				break;
			}
		}
		
	}
	
	public void updateMovieReview(String user,String review,int indexMovie){
		String reviewText = listMovieRated.get(indexMovie).getReview();
		reviewText+="Name: "+user+"\n";
		reviewText+=review+"\n";
		reviewText+="\n-------------------------------------------\n";
		Movie movie = listMovieRated.get(indexMovie);
		movie.setReview(reviewText);
	}

	public void printTopFiveMovieBasedOnTicket(){
		//insertion sort to sort the movie  
		for(int i = 1 ; i < listMovieRated.size() ; i++){
			for(int j = i ; j > 0 ;j--){
				double first = listMovieRated.get(j-1).getTicketSales();
				double second = listMovieRated.get(j).getTicketSales();
				
				if(first < second){
					//perform swap process
					Movie buffer = listMovieRated.get(j);
					listMovieRated.set(j, listMovieRated.get(j-1));
					listMovieRated.set(j-1,buffer);
				}
			}
		}
		for(int i = 0 ; i< listMovieRated.size() && i <5 ;i++){
			System.out.println(i+1+")"+" Title: "+listMovieRated.get(i).getTitle()+" ticket: "+listMovieRated.get(i).getTicketSales());
		}
	}
	
	public void printTopFiveMovieBasedOnRating(){
		//insertion sort to sort the movie  
	
		for(int i = 1 ; i < listMovieRated.size() ; i++){
			for(int j = i ; j > 0 ;j--){
				double first = listMovieRated.get(j-1).getRating();
				double second = listMovieRated.get(j).getRating();
				if(first < second){
					//perform swap process
					System.out.println("test");
					Movie buffer = listMovieRated.get(j);
					listMovieRated.set(j, listMovieRated.get(j-1));
					listMovieRated.set(j-1,buffer);
				}
			}
		}
		

		for(int i = 0 ; i< listMovieRated.size() ;i++){
			if(listMovieRated.get(i).getCounterRating() > 1){
				System.out.printf("%d) Title: %s, Rating: %.1f\n",i+1,listMovieRated.get(i).getTitle(),listMovieRated.get(i).getRating());
			}else{
				System.out.println(i+1+") "+"Title: "+listMovieRated.get(i).getTitle()+", Rating: NA");
			}
			
			}
			
	}
	
	
	public void printMovieList(){
		for(int i = 0 ; i< listMovieRated.size() ;i++){
			System.out.println(i+1+")"+"Title: "+listMovieRated.get(i).getTitle()+", Rating: "+listMovieRated.get(i).getRating());
		}
	}
	
	public void printNormalMovieList(){
		for(int i = 0 ; i< listMovieRated.size() ;i++){
			System.out.println(i+1+")"+"Title: "+listMovieRated.get(i).getTitle());
		}
	}
	
	public void printReview(int index){
		System.out.println("Title: "+listMovieRated.get(index).getTitle());
		System.out.println(listMovieRated.get(index).getReview());
		
	}
	
}