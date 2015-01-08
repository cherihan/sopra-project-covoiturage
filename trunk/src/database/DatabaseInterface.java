package database;

import java.util.*;

import model.*;


public interface DatabaseInterface {

	//ici on trouve la définitions de tous les signaux que mélina peux envoyer à alex
	//pas d'implémentation que des définitions
	
	//Retourne 0 si pas enregistré, 1 si enregistré, 2 si c'est un admin (-1 si erreur)
	public User requestUserIsRegistered(EmailAdresse mail, MotDePass pass) throws RequestDidNotWork;
	
	//renvoie un tableau de table contenant tous les trajets correspondant à chaque jours de la semaine 
	//EI = 
	//Tableau = {ridesLundi, ridesMardi,....}
	//à ceux de l'utilisateur donné (null si pas de correspondance) 
	public ArrayList <ArrayList<Ride>> requestUserWeeklyPossibleRides(User user) throws RequestDidNotWork;
	
	//vu que pour l'instant je sais pas quelles sont les infos obligatoires je mets ça
	//retourne -2 si erreur, sinon un truc positif (l'id)
	public int newAccount(User user, MotDePass pass) throws RequestDidNotWork;
	
	
}
