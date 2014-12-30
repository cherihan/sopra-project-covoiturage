package controler;

public interface ServletsToControler {
	
	//ici il y auras tous les signaux que Tristan peux appeler chez melina 
	//Tout est juste défini, pas implémenté
	//A tristan de définir les signaux, mélina tu les implémente dans Controler
	
	/*Exemple : creatAccount(NomDuType,sonAge, saphoto....)
				addRide(depart,arrivé,....)
				modifieAccount(id,champ1,champ2...)
	
	
	*/
	/**
	 * If user is in the data base this function sends back it's userID, otherwise it returns -1
	 * @param userName
	 * @param pwd
	 * @return userID or -1 if not a correct user
	 */
	public int userIdAndPasswordAregood(String userName, String pwd);
	
	/**
	 * Checks if the user is an admin of not 
	 * @param userID
	 * @return true if is a admin of the site
	 */
	public boolean isAdmin(int userID);
	
}
