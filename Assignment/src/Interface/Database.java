package Interface;

import java.util.ArrayList;


public interface Database {

	public void writeToDatabase(String filename,ArrayList<Object> list);
	public ArrayList readFromDatabase(String filename);
	
}
