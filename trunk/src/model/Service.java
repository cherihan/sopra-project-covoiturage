package model;

import java.util.ArrayList;


public class Service {
	
	private String nom;
	private int id; //id dans la base de données
	private String description;
	private Adresse addr; 
	
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
	public String toString(){
		return "id : "+id+", nom : "+nom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Adresse getAddr() {
		return addr;
	}
	public void setAddr(Adresse addr) {
		this.addr = addr;
	}
	public boolean equals (Service serv){
		return this.id == serv.getId();
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