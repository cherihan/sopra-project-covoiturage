package model;

public class Adresse {
	//pour le momen on ne gère que les poste code
	private PostCode postCode;
	private String ville;
	private String rue;
	
	
	public Adresse(PostCode postCode){
		this.postCode = postCode;
	}
	
	public PostCode getPostCode(){
		return this.postCode;
	}
}
