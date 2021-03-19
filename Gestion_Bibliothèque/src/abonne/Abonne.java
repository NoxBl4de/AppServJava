package abonne;

import bibliotheque.IAbonne;

public class Abonne implements IAbonne {

	static int numeros = 1;
	private int num;
	
	public Abonne() {
		num = numeros++;
	}
	
	/**
	 * Recup�re le num�ro de l'abonn�
	 */
	@Override
	public int num() {
		return num;
	}
	
}
