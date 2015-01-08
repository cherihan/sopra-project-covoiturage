package model;

public class Adresse {
	//pour le momen on ne gère que les poste code
	private PostCode postCode;
	private String ville;
	private String rue;
	
	
	public Adresse(PostCode postCode){
		this.postCode = postCode;
	}
	public Adresse(PostCode postCode, String rue, String ville){
		this.postCode = postCode;
		this.rue = rue;
		this.ville = ville;
	}
	
	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public void setPostCode(PostCode postCode) {
		this.postCode = postCode;
	}

	public PostCode getPostCode(){
		return this.postCode;
	}
}
