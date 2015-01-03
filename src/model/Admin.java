package model;

public class Admin extends User {
	
	public Admin(int id, String lastName, String firstName, PostCode postCode,
			String service){
		super(id,lastName,firstName,postCode,service);
	}
	
	public Admin(User user){
		
		
		//check avec la database que c'est bien un admin et la c'est parti sinon on nic tout ! 
		super(66,"lalala","llala",new PostCode(32222),"lala");
	}

}
