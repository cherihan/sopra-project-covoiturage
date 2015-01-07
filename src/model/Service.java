package model;

import java.util.ArrayList;


public class Service {
	
	private String nom;
	private int id; //id dans la base de données
	private String description;
	private Adresse addr; 
	
	//ne pas utiliser de consctructeur, jamais 
	public Service (){
		this.id = 0;
	}
	public Service (int id){
		this.id = id;
	}
	public Service (int id, String nom, String description, Adresse addr){
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.addr = addr;
	}
	
	/*
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
	}*/
}