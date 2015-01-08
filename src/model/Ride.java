package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;



public class Ride {
	private int id;
	private User user;
	private Adresse home;
	private Service office;	
	private JourDeLaSemaine jour;
	private Heure atOfficeAt;//heur à laquelle on pense arriver ou partir du travail 
	private boolean sens; //true = allé, retour = false
	private String comment;
	
	public Ride(int id,User conducteur, Adresse home, Service office,JourDeLaSemaine jour, 
			Heure hour, Boolean sens, String com) {
		this.id = id;
		this.user = conducteur;
		this.home =  home;
		this.office = office; //on a pas besion de vérifier, on part du principe que le controler (servlet) l'aura fait
		this.jour = jour;
		this.atOfficeAt = hour;		
		this.sens = sens ;
		this.comment = com;
		
		
		System.out.println("###DEBUG ### (Ride, constructeur) : ride créer");
	}

	// Display ride (toString est générique) 
	public String toString () {
		String res;
		res="###DEBUG ### (Ride, toString) \n";
		res="Le conduteur est :"+this.user+"\n";
		res+="Le point de départ se situe à : " + this.home+"\n";
		res+="Le point d'arrivée se situe dans le office : "+ this.office+"\n";
		if (sens){
			res+="Dans le sens allé a "+this.atOfficeAt+"\n";
		}else {
			res+="Dans le sens retour \n";
		}
		return res;
	}

	// Add a postCode to the Ride
	public void setAdresse(Adresse homeAddr) {
		this.home = homeAddr;
	}

	// Add a office to the Ride
	public void setoffice(Service office) {
		this.office = office;
	}

	/**Informes if the ride is from home to office or from office to home
	 * 
	 * @return true = home -> office ; false office -> home 
	 */
	public boolean getSens () {
		return this.sens;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Adresse getHome() {
		return home;
	}

	public void setHome(Adresse home) {
		this.home = home;
	}

	public Service getOffice() {
		return office;
	}

	public void setOffice(Service office) {
		this.office = office;
	}

	public JourDeLaSemaine getJour() {
		return jour;
	}

	public void setJour(JourDeLaSemaine jour) {
		this.jour = jour;
	}

	public Heure getAtOfficeAt() {
		return atOfficeAt;
	}

	public void setAtOfficeAt(Heure atOfficeAt) {
		this.atOfficeAt = atOfficeAt;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	/*public static void main(String[] args) throws Exceptions {
		try {
			offices.listeoffices.add("bureau");
			PostCode code1 = new PostCode(31000);
			PostCode code2 = new PostCode(11000);

			Ride ride1 = new Ride(code1, "bureau", true);
			Ride ride2 = new Ride(code2, "burea", false);
			ride1.displayRide();
			ride2.displayRide();
		} catch (NullPointerException e) {
			// System.out.println("pointeur vide");
		}
	};*/
}
