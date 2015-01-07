package database;

public class RequestDidNotWork extends Exception{
	
	public RequestDidNotWork (String message){
		super();
		System.err.println(message);;
	}

}
