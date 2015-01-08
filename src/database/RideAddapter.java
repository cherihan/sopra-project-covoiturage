package database;

import java.util.HashMap;
import model.*;

public class RideAddapter {
	
	private HashMap<String, String> adapted; 
	
	public RideAddapter(){
		adapted = new HashMap<String, String>();
	}
	
	public HashMap<String, String> adaptRideToHashMap(Ride ride){
		
		adapted.put("id", String.valueOf(ride.getId()));
		adapted.put("source", ride.getHome().getPostCode().toString());
		adapted.put("dest", String.valueOf(ride.getOffice().getId()));
		
		
		
		return adapted;
	}
	

}
