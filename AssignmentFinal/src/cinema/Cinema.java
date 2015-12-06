package cinema;

import java.io.Serializable;

/**
	Represents a cinema in a Cineplex.
	Each Cinema has 100 CinemaSeat.
	@author Chee Yi Xu
	@version 1.0
	@since 2015-11-01
*/

public class Cinema implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	/**
	 * The name of this cinema.
	 */
	private String cinemaName = "";
	/**
	 * The total number of seats.
	 */
	private CinemaSeat[][] seatArray = new CinemaSeat[10][10]; 
	private String theaterType;
	
	
	//The main method is used to debug
	public static void main(String[] args) {
		Cinema cinema = new Cinema("");
		//debug to get the class type		
		Cinema cin = new Cinema();		
		System.out.println(cin.getSeatArrangement()); 
		
	}
	/**
	 * Creates a new Cinema.
	 * Initialize all the seats to be empty.
	 */
	public Cinema(){		
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				
				seatArray[i][j] = new CinemaSeat(i + 1, j + 1, true);
			}		
			
		}
	}
	/**
	 * Creates a new Student with given name and set the theater type.
	 * @param cinemaName This Cinema's name.
	 * @param theaterType This Cinema's theater type.
	 */
	public Cinema(String cinemaName , String theaterType){
		this.cinemaName = cinemaName;	
		this.theaterType = theaterType;
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				
				seatArray[i][j] = new CinemaSeat(i + 1, j + 1, false);
			}			
		}
	}
	/**
	 * Creates a new Student with given name.
	 * @param cinemaName This Cinema's name.
	 */
	public Cinema(String cinemaName){
		this.cinemaName = cinemaName;
	
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				seatArray[i][j] = new CinemaSeat(i + 1, j + 1, false);
			}		
			
		}					
	}
	/**
	 * Changes the name of this Cinema.
	 * This may involve a lengthy legal process.
	 * @param cinemaName This Cinema's new name.
	 */	 
	
	public void setCinemaName(String cinemaName){
		this.cinemaName = cinemaName;
	}
	
	/**
	 * Gets name of this Cinema..
	 * @return this Cinema's name.
	 */
	
	public String getCinemaName(){
		
		return this.cinemaName;
		
	}
	/**
	 * Gets theater type of this Cinema..
	 * @return this Cinema's theater type.
	 */
	public String getTheaterType(){
		return this.theaterType;
	}
	/**
	 * Gets seat arrangement of this Cinema.
	 * @return this Cinema's seat arrangement.
	 */
	//This method will return the layout of the cinema
	public String getSeatArrangement(){
		
		String result = "";
		int row = 0;
		int column = 0;
		
		result += "\t\t    ____________________________ \n"
				+ "\t\t   |           Screen           |\n"
				+ "\t\t   |____________________________|\n";
		
		result += "\n    ___________     ___________________________________     ___________\n";
				
		for (row = 0; row < 10; row++){			
			result += seatArray[row][column].getRow();
			
			for (int j = 0; j < 10; j++){
				
				if(j > 7){
					if (seatArray[row][j].isOccupied())
						result += "  |  X";					
					else
						result += "  |   ";
				}
				else if (j > 1 && j < 8){
					if (seatArray[row][j].isOccupied())
						result += " | X  ";					
					else 
						result += " |    ";
					if (j == 7)
						result += " | ";
				}
				else{
					
					if (seatArray[row][j].isOccupied())
						result += "  | X ";					
					else 
						result += "  |   ";
					if (j == 1)
						result += "  |  ";
					
				}
				
				
			}
			result += "  |";
			result += "\n   |_____|_____|   |_____|_____|_____|_____|_____|_____|   |_____|_____|\n";
			
		}
		
		result += "\n "
				+ "     1"
				+ "     2"
				+ "         3"
				+ "     4"
				+ "     5"
				+ "     6"
				+ "     7"
				+ "     8 "
				+ "       9"
				+ "      10";
		
		return result;
		
	}
	/**
	 * Check whether a CinemaSeat is occupied.
	 * @param row Row to request.
	 * @param column Column to request.
	 * @return False if the seat is occupied, True if the seat is not occupied.
	 */
	//This method will check whether a seat is taken
	public boolean requestSeat(String row, int column){
		
		switch(row){
		
			case "A": 				
				if (seatArray[0][column -1].isOccupied()){
					return false;
				}
				seatArray[0][column -1].setOccupied(true);
				break;
			case "B": 
				if (seatArray[1][column -1].isOccupied()){
					return false;
				}
				seatArray[1][column -1].setOccupied(true);
				break;
			case "C": 
				if (seatArray[2][column -1].isOccupied()){
					return false;
				}
				seatArray[2][column -1].setOccupied(true);
				break;
			case "D": 
				if (seatArray[3][column -1].isOccupied()){
					return false;
				}
				seatArray[3][column -1].setOccupied(true);
				break;
			case "E": 
				if (seatArray[4][column -1].isOccupied()){
					return false;
				}
				seatArray[4][column -1].setOccupied(true);
				break;
			case "F": 
				if (seatArray[5][column -1].isOccupied()){
					return false;
				}
				seatArray[5][column -1].setOccupied(true);
				break;
			case "G": 
				if (seatArray[6][column -1].isOccupied()){
					return false;
				}
				seatArray[6][column -1].setOccupied(true);
				break;
			case "H": 
				if (seatArray[7][column -1].isOccupied()){
					return false;
				}
				seatArray[7][column -1].setOccupied(true);
				break;
			case "I": 
				if (seatArray[8][column -1].isOccupied()){
					return false;
				}
				seatArray[8][column -1].setOccupied(true);
				break;
			case "J": 
				if (seatArray[9][column -1].isOccupied()){
					return false;
				}
				seatArray[9][column -1].setOccupied(true);
				break;
				
		
		}
		
		
		return true;
		
	}
		
}
