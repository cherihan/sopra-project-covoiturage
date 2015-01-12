package database;

public class NotAdmin extends Exception{
	
	public NotAdmin(){
		super();
		System.err.println("The user is not an admin");
	}

}
