package AccountManagement;


import java.util.ArrayList;
import Datas.PostCode; 

public class User {

	private int id;
	Boolean admin;
	String lastName;
	String firstName;
	PostCode postCode; // proposer une liste de code postaux OU utiliser google
						// map
	String service; // choix dans une liste d√©roulante du service dans lequel
					// travaille la personne chez Sopra
	// Liste contenant les trajets de la semaine 
	ArrayList<Ride> weekRides = new ArrayList<Ride>();

	public User(int id, String lastName, String firstName, PostCode postCode,
			String service) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.admin = false;
		// VÈrifier si le code postal est bon
		this.postCode = postCode;
		// VÈrifier si le service est bon 
		this.service = service;
	}
}