package database;

import java.util.*;

import model.*;


public interface DatabaseInterface {

	//ici on trouve la d�finitions de tous les signaux que m�lina peux envoyer � alex
	//pas d'impl�mentation que des d�finitions
	
	//Retourne 0 si pas enregistr�, 1 si enregistr�, 2 si c'est un admin (-1 si erreur)
	public User requestUserIsRegistered(EmailAdresse mail, MotDePass pass) throws RequestDidNotWork;
	
	//renvoie un tableau de table contenant tous les trajets correspondant � chaque jours de la semaine 
	//EI = 
	//Tableau = {ridesLundi, ridesMardi,....}
	//� ceux de l'utilisateur donn� (null si pas de correspondance) 
	public ArrayList <ArrayList<Ride>> requestUserWeeklyPossibleRides(User user) throws RequestDidNotWork;
	
	//vu que pour l'instant je sais pas quelles sont les infos obligatoires je mets �a
	//retourne -2 si erreur, sinon un truc positif (l'id)
	public int newAccount(User user, MotDePass pass) throws RequestDidNotWork;
	
	
}
