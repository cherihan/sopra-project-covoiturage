package AccountManagement;

import java.util.ArrayList;
import Datas.Services;
import Datas.PostCode;


public class Ride {
	PostCode postCode;
	String service;
	Boolean retour;
	private static Services services = new Services();

	public Ride(PostCode postCode, String service, Boolean retour) {
		this.postCode = postCode;
		this.retour = retour;
		if (services.listeServices.contains(service)) {
			this.service = service;
		} else {
			System.out
					.println("le service que vous essayez de rentrer ne fait pas parti de la liste");
		}
	}

	// Display ride
	public void displayRide() {
		System.out.println("Le point de départ se situe à : " + this.postCode.code);
		System.out.println("Le point d'arrivée se situe dans le service : "
				+ this.service);
		System.out.print("Le retour est : ");
		if (this.retour) {
			System.out.println("activé ");
		} else {
			System.out.print("désactivé ");
		}
	}

	// Add a postCode to the Ride
	public void addPostCode(PostCode postCode) {
		this.postCode = postCode;
	}

	// Add a service to the Ride
	public void addService(String service) {
		if (services.listeServices.contains(service)) {
			this.service = service;
		} else {
			System.out
					.println("le service que vous essayez de rentrer ne fait pas parti de la liste");
		}
	}

	// Get the value if the return exists or not
	public boolean getReturn() {
		return this.retour;
	}

	/*public static void main(String[] args) throws Exceptions {
		try {
			services.listeServices.add("bureau");
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
