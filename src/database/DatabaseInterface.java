package database;

import java.util.*;

import model.Ride;

public interface DatabaseInterface {

	//ici on trouve la définitions de tous les signaux que mélina peux envoyer à alex
	//pas d'implémentation que des définitions
	
	//Retourne 0 si pas enregistré, 1 si enregistré, 2 si c'est un admin (-1 si erreur)
	public int requestUserIsRegistered(String username, String password);
	
	//renvoie un tableau contenant tous les trajets correspondant (même code postal et même site sopra)
	//à ceux de l'utilisateur donné (null si pas de correspondance)
	public ArrayList<Ride> requestUserRides(String username);
	
	
}
