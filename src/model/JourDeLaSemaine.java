package model;

public class JourDeLaSemaine {
	
	private int jour;
	private String string;
	
	
	public JourDeLaSemaine (int jourNum){
		if(jourNum < 8 && jourNum > 0){
			this.jour = jourNum;
		}		
	}
	public JourDeLaSemaine (int jourNum, String string){
		this.jour = jourNum;
		this.string = string;	
	}
	
	
	
	public String toString(){
		String res;
		switch (this.jour) {
		case 1:
			res ="lundi";
			break;
		case 2:
			res = "mardi";
			break;
		case 3 :
			res = "mercredi";
			break;
		case 4 :
			res="jeudi";
			break;
		case 5 :
			res= "vendredi";
			break;
		case 6 :
			res= "samedi";
			break;
		case 7 :
			res = "dimanche";

		default:
			res = "Il y a ue un gros problème un jour et la c'est grave (model.JourDeLASemaine)";
			break;
		}		
		return res;
	}
	
	public int getJour(){
		return jour;
	}
}
