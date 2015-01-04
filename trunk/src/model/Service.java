package model;

import java.util.ArrayList;


public class Service {
	
	private String nom;
	private int id; //id dans la base de données
	private String description;
	private Adresse addr; 
	
	
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