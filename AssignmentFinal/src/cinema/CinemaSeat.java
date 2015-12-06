package cinema;

import java.io.Serializable;

/**
	Represents a Cinema Seat in a Cinema.
	Each Cinema Seat has a row id and column id.
	@author Chee Yi Xu
	@version 1.0
	@since 2015-11-01
*/

public class CinemaSeat implements Serializable {

	/**
	 * The row id and column id of this seat.
	 */
	private int row, column;
	/**
	 * Whether the seat is occupied.
	 */
	private boolean occupied;	
	/**
	 * Id of the customer taking this seat
	 */
	private int customerId;		
	
	/**
	 * Creates a new Cinema Seat with given row id and column id.
	 * @param row This Cinema Seat's row id.
	 * @param column This Cinema Seat's column id.
	 */
	public CinemaSeat(int row, int column){
		
		this.row = row;
		this.column = column;
		
	}
	
	/**
	 * Creates a new Cinema Seat with given row id and column id.
	 * @param row This Cinema Seat's row id.
	 * @param column This Cinema Seat's column id.
	 * @param occupied This Cinema Seat's availability.
	 */
	public CinemaSeat(int row, int column, boolean occupied){
		
		this.row = row;
		this.column = column;
		this.occupied = occupied;
		
	}
	
	/**
	 * Check Cinema Seat's availability
	 * @return true is the seat is occupied, false otherwise.
	 */
	public boolean isOccupied(){
		
		return occupied;
		
	}	
	/**
	 * Gets this Cinema Seat's Id.
	 * @return this Cinema Seat's Id.
	 */
	public String getSeatId(){
		
		return this.getRow() + Integer.toString(column);
		
	}
	/**
	 * Gets this Cinema Seat's row id in capital letter form.
	 * 0-9 corresponding to A-J
	 * @return this Cinema Seat's Id in capital letter form.
	 */
	public char getRow(){
		// We want to return the alphabetic instead of the row number
		// because it will be nicer.
		switch (row){
		
			case 1: 
				return 'A';
			case 2: 
				return 'B';
			case 3: 
				return 'C';
			case 4: 
				return 'D';
			case 5: 
				return 'E';
			case 6: 
				return 'F';
			case 7: 
				return 'G';
			case 8: 
				return 'H';
			case 9: 
				return 'I';
			case 10: 
				return 'J';
			default:
				return ' ';
		
		}		
		
	}
	/**
	 * Gets this Cinema Seat's column id.
	 * @return this Cinema Seat's column id.
	 */
	public int getColumn(){
		
		return column;
		
	}
	
	/**
	 * Free the Cinema Seat.
	 */
	public void freeSeat(){
		
		occupied = false;
		
	}
	/**
	 * Set the Cinema Seat.
	 */
	public void setOccupied (boolean occupied){
		
		this.occupied = occupied;
		
	}
	
}