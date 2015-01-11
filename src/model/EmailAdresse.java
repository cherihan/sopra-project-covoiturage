package model;

public class EmailAdresse {
	private String email;
	

	
	public EmailAdresse (String email){
		this.email = email;
	}
	public String toString(){
		return this.email; 
	}
	public boolean equals(EmailAdresse e){
		return this.email.equals(e);
	}
}
