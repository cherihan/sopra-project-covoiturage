package database;

public interface DatabaseInterface {

	//ici on trouve la d�finitions de tous les signaux que m�lina peux envoyer � alex
	
	//pas d'impl�mentation que des d�finitions
	
	//Retourne 0 si pas enregistr�, 1 si enregistr�, 2 si c'est un admin (-1 si erreur)
	public int requestUserIsRegistered(String username, String password);
	
	
}
