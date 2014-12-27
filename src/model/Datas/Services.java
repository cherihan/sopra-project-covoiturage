package Datas;

import java.util.ArrayList;


public class Services {
	public ArrayList<String> listeServices = new ArrayList<String>();
	
	// To add a service to the list of service
	public void addService (String ajoutService) {
		listeServices.add(ajoutService);
	}
	
	public boolean contains (String service) {
		boolean retour ;
		if (listeServices.contains(service)) {
			retour = true ;
		}
		else { retour = false ; }
		return retour ; 
	}
}
