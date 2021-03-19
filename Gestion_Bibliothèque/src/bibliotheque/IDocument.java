package bibliotheque;

import document.PasLibreException;

public interface IDocument {
	int numero();
	void reserver(IAbonne ab) throws PasLibreException;
	void emprunter(IAbonne ab) throws PasLibreException;
	void retour(); // document rendu ou annulation réservation
}
