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
		//Address
		adapted.put("homeCP", ride.getHome().getPostCode().toString());
		adapted.put("homeRue", ride.getHome().getRue());
		adapted.put("homeVille", ride.getHome().getVille());
		
		//Heure, commentaire
		adapted.put("heure", ride.getAtOfficeAt().toDBFormat() );
		adapted.put("commentaire",ride.getComment() );
		
		
		
		return adapted;
	}
	

}
