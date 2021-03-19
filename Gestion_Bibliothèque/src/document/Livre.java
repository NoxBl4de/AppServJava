package document;

import bibliotheque.IAbonne;
import bibliotheque.IDocument;

public class Livre implements IDocument {
	
	static int numeros = 1;

	private EtatDocument status;
	private int numero;
	private Integer numAbo;
	
	public Livre() {
		status = EtatDocument.Disponible;
		numero = numeros++;
		numAbo = null;
	}
	
	/**
	 * Recup�re le numero du document
	 */
	@Override
	public int numero() {
		// TODO Auto-generated method stub
		return numero;
	}

	/**
	 * Rendre le doc
	 */
	@Override
	public void retour() {
		// TODO Auto-generated method stub
		status = EtatDocument.Disponible;
		System.out.println("Retour du document n�" + numero);
	}

	/**
	 * Reservation du doc
	 * @param ab : l'abonne souhaitant reserver
	 */
	@Override
	public void reserver(IAbonne ab) throws PasLibreException {
		// TODO Auto-generated method stub
		if (status == EtatDocument.Emprunt� || status == EtatDocument.R�serv�)
			throw new PasLibreException();
		
		else {
			status = EtatDocument.R�serv�;
			numAbo = ab.num();
			System.out.println("Reservation du document n�" + numero + " par l'abonn� n�" + numAbo);
		}
	}

	/**
	 * Emprunt du doc
	 * @param ab : l'abonn� souhaitant emprunter
	 */
	@Override
	public void emprunter(IAbonne ab) throws PasLibreException {
		// TODO Auto-generated method stub
		if (status == EtatDocument.Emprunt� || (status == EtatDocument.R�serv� && ab.num() != numAbo))
			throw new PasLibreException();
		
		else {
			status = EtatDocument.Emprunt�;
			numAbo = ab.num();
			System.out.println("Emprunt du document n�" + numero + " par l'abonn� n�" + numAbo);
		}
	}

}
