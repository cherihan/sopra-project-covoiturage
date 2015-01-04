package database;

import java.util.*;

import model.Ride;

public interface DatabaseInterface {

	//ici on trouve la d�finitions de tous les signaux que m�lina peux envoyer � alex
	//pas d'impl�mentation que des d�finitions
	
	//Retourne 0 si pas enregistr�, 1 si enregistr�, 2 si c'est un admin (-1 si erreur)
	public int requestUserIsRegistered(String mail);
	
	//renvoie un tableau contenant tous les trajets correspondant (m�me code postal et m�me site sopra)
	//� ceux de l'utilisateur donn� (null si pas de correspondance)
	public ArrayList<Ride> requestUserRides(String mail);
	
	//vu que pour l'instant je sais pas quelles sont les infos obligatoires je mets �a
	//retourne -2 si erreur, sinon un truc positif (l'id je crois)
	public int newAccount(String lastName, String firstName, String email, String bio);
	
	
}
