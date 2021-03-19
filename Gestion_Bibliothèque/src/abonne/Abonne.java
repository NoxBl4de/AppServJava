package abonne;

import bibliotheque.IAbonne;

public class Abonne implements IAbonne {

	static int numeros = 1;
	private int num;
	
	public Abonne() {
		num = numeros++;
	}
	
	/**
	 * Recupère le numéro de l'abonné
	 */
	@Override
	public int num() {
		return num;
	}
	
}
