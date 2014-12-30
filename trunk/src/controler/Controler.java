package controler;

public class Controler implements ServletsToControler{

	//ici on met toute l'implémentation des signaux qui sont dans le ServletsToControler
	
	
	public int userIdAndPasswordAregood(String userName, String pwd){
		return 666;
	}
	public boolean isAdmin(int userID){
		return true;
	}
}
