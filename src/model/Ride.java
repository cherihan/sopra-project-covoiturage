package model;

import java.util.ArrayList;



public class Ride {
	private Adresse home;
	private Service office;
	private boolean sens; //true = allé, retour = false

	public Ride(Adresse home, Service office, Boolean sens) {
		this.home =  home;
		this.office = office; //on a pas besion de vérifier, on part du principe que le controler (servlet) l'aura fait
		this.sens = sens ;
		
		
		System.out.println("###DEBUG ### (Ride, constructeur) : le office que vous essayez de rentrer ne fait pas parti de la liste");
	}

	// Display ride (toString est générique) 
	public String toString () {
		String res;
		res="###DEBUG ### (Ride, toString) \n";
		res+="Le point de départ se situe à : " + this.home+"\n";
		res+="Le point d'arrivée se situe dans le office : "+ this.office+"\n";
		if (sens){
			res+="Dans le sens allé \n";
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
