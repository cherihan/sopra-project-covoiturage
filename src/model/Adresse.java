package model;

public class Adresse {
	//pour le momen on ne g�re que les poste code
	private PostCode postCode;
	
	
	public Adresse(PostCode postCode){
		this.postCode = postCode;
	}
	
	public PostCode getPostCode(){
		return this.postCode;
	}
}
