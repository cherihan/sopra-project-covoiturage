package model;

import java.util.ArrayList;

public class PostCode {
	public Integer code;

	public PostCode(int code) {
		this.code = code;
	}

	static Boolean PostCodeVerif(Integer saisie, ArrayList list) {
		boolean retour = false;

		// Vérification du nombre de chiffres présents dans la saisie
		if (saisie.SIZE != 5) {
			if (list.contains(saisie)) {
				retour = true;
			}
		} else {
			retour = false;
		}
		return retour;
	}

	/*
	 * public static void main (String[] args) { try { ArrayList<Integer> liste
	 * = new ArrayList<Integer>(); liste.add(11000);
	 * System.out.println("la valeur retournée doit etre : FALSE");
	 * System.out.println(PostCodeVerif(31000, liste)); } catch (Error
	 * ErrorType) {
	 * System.out.println("la valeur saisie n'est pas de type entier"); } //
	 * traiter les exceptions de type }
	 */
}
