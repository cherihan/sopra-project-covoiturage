package model;


import java.util.ArrayList;


public class User {

	private int id;
	//comme on a le type admin particulier on est pas obliger de memo ça jhe pesnse
	//private Boolean admin;
	private String lastName;
	private String firstName;
	private String bio;
	private String email; //proprement il faudrait créer un type e-mail adress
	
	//Pour moi ça fait parti des informations sur un ride pas sur le user 
	/*
	PostCode postCode; // proposer une liste de code postaux OU utiliser google
						// map
	String service; // choix dans une liste dÃ©roulante du service dans lequel
					// travaille la personne chez Sopra
	
	// Liste contenant les trajets de la semaine */
	ArrayList<Ride> weekRides = new ArrayList<Ride>();
	/**Full constructeur that can set a user from scratsh
	 * 
	 * @param id
	 * @param lastName
	 * @param firstName
	 * @param Bio
	 */
	public User(int id, String lastName, String firstName, String email, String bio) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.bio = bio;
		
		//this.admin = false;
		/*
		// Vérifier si le code postal est bon
		this.postCode = postCode;
		// Vérifier si le service est bon 
		this.service = service;
		*/
	}
	
	
	public User (int userID){
		//get from the data base the infos
		// yet it is simulated 
		this.id = userID;
		this.lastName = "Christ";
		this.firstName = "Jesus";
		this.bio = "le fils de dieux";
		this.email = "the.police.sting@hotmail.com";
		
		
		/*
		this.admin = true;
		this.postCode = new PostCode(31770);
		this.service = "paradie";*/
	}
	public User (String email){
		//get from the data base the infos
		// wet it is simulated 
		this.id = 666;
		this.lastName = "Christ";
		this.firstName = "Jesus";
		this.bio = "le fils de dieux";
		this.email = "the.police.sting@hotmail.com";
		/*this.admin = true;
		this.postCode = new PostCode(31770);
		this.service = "paradie";*/
	}
	
	public int getID (){
		return this.id;
	}
	/*public boolean isAdmin(){
		return admin;
	}*/
	public String toString (){
		return this.lastName+" "+this.firstName+" : "+this.id+" ("+this.email+")\n";
	}
}