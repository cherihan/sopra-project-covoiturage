package model;

import java.util.ArrayList;

public class PostCode {
	public Integer code;

	public PostCode(int code) {
		this.code = code;
	}

	static Boolean PostCodeVerif(Integer saisie, ArrayList list) {//du coup la liste devrai �tre un variable de class non?
		boolean retour = false;

		// V�rification du nombre de chiffres pr�sents dans la saisie
		if (saisie.SIZE != 6) {
			if (list.contains(saisie)) {
				retour = true;
			}
		} else {
			retour = false;
		}
		return retour;
	}

	public String toString(){
		return String.valueOf(this.code);
	}
	
	/*
	 * public static void main (String[] args) { try { ArrayList<Integer> liste
	 * = new ArrayList<Integer>(); liste.add(11000);
	 * System.out.println("la valeur retourn�e doit etre : FALSE");
	 * System.out.println(PostCodeVerif(31000, liste)); } catch (Error
	 * ErrorType) {
	 * System.out.println("la valeur saisie n'est pas de type entier"); } //
	 * traiter les exceptions de type }
	 */
}
