package Mail;

import java.util.ArrayList;

import model.Ride;

public class texteMail {

	String destinataire ;
	String mode;
	String texte;
	String objet;
	Ride ride; 


	public ArrayList<String> constructionTexte(String destinataire, String mode/*, Ride ride*/) {
		this.destinataire=destinataire;
		this.mode=mode;
		this.ride=ride;
		
		ArrayList<String> liste  = new ArrayList <String> ();
	
		
		if (mode.equals("postRide")) {
			this.objet = "Publication de votre trajet effective";
			this.texte = "Bonjour \n \n Vous venez de publier un trajet : Le point de départ se situe";
		    System.out.println(ride.toString());
		}

		if (mode.equals("alertRide")) {
			this.objet = "Un trajet pourrait vous intéresser";
			this.texte = "Bonjour \n \n Un trajet a été posté sur le site et serait susceptible de vous intéresser";
		}
		
		liste.add(0,objet);
		liste.add(1,texte);
		return liste ;
	}

}
