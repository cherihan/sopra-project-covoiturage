package model;

public class Admin extends User {
	
	public Admin(){
		super(32,"Sopra","JC","the.police.sting@hotmail.com","peut tout faire");
	}
	
	
	
	
	public Admin(User user){
		
		//check avec la database que c'est bien un admin et la c'est parti sinon on nic tout ! 
		super(32,"Sopra","JC","the.police.sting@hotmail.com","peut tout faire");
	}

}
