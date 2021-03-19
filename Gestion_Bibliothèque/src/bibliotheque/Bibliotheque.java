package bibliotheque;

import java.util.ArrayList;
import java.util.List;

import abonne.Abonne;
import document.Livre;

public class Bibliotheque {

	private List<IDocument> documents;
	private List<IAbonne> abonnes;
	private boolean booked;

	/**
	 * Constructeur avec initialisation des listes
	 */
	public Bibliotheque() {
		documents = new ArrayList<IDocument>();
		abonnes = new ArrayList<IAbonne>();
		remplirAbos(abonnes);
		remplirDocs(documents);
		booked = false;
	}

	/**
	 * Rendre un document
	 * @param numDoc : numéro du doc à rendre
	 * @throws Exception
	 */
	public synchronized void retour(int numDoc) throws Exception {
		IDocument d = searchDoc(numDoc);
		d.retour();
	}

	/**
	 * Reserver un document
	 * @param numAb : numero d'abonné
	 * @param numDoc : numéro de doc
	 * @throws Exception si doc inexistant
	 */
	public synchronized void reserver(int numAb, int numDoc) throws Exception {
		if (verifNumAbo(numAb) == true) {
			IDocument d = searchDoc(numDoc);
			d.reserver(searchAbo(numAb));
			booked = false;
			wait(30000);
			if (booked == false) {
				System.err.println("Délai de réservation dépassé !");
				d.retour();
			}
		}

		else System.err.println("Numéro d'abonné ou de document incorrect");
	}

	/**
	 * Emprunter un document
	 * @param numAb : numero d'abonné
	 * @param numDoc : numéro de doc
	 * @throws Exception si doc inexistant
	 */
	public synchronized void emprunter(int numAb, int numDoc) throws Exception {
		if (verifNumAbo(numAb) == true) {
			IDocument d = searchDoc(numDoc);
			d.emprunter(searchAbo(numAb));
			booked = true;
			notifyAll();
		}

		else System.err.println("Numéro d'abonné ou de document incorrect");
	}

	/**
	 * Verification du num d'abonné
	 * @param num : le numéro d'abonné
	 * @return boléen
	 */
	public boolean verifNumAbo(int num) {
		boolean verif = false;

		for (IAbonne abo : abonnes) {
			if (abo.num() == num)
				verif = true;
		}

		return verif;
	}

	private IDocument searchDoc(int num) throws Exception {
		IDocument doc = null;

		for (IDocument d : documents) {
			if (d.numero() == num)
				doc = d;
		}

		if (doc == null) 
			throw new Exception("document inexistant"); 

		return doc;
	}

	/**
	 * Recherche d'un abo
	 * @param num
	 * @return l'abonné trouvé ou pas
	 * @throws Exception
	 */
	private IAbonne searchAbo(int num) throws Exception {
		IAbonne abo = null;

		for (IAbonne a : abonnes) {
			if (a.num() == num)
				abo = a;
		}

		if (abo == null) 
			throw new Exception("abonné inexistant"); 

		return abo;
	}

	/**
	 * Initialisation des livres
	 * @param doc : liste de doc à remplir
	 */
	private void remplirDocs(List<IDocument> doc) {
		for (int i = 0; i < 8; ++i)
			doc.add(new Livre());
	}

	/** 
	 * Initialisation des abonnés
	 * @param abo : liste d'abo à remplir
	 */
	private void remplirAbos(List<IAbonne> abo) {
		for (int i = 0; i < 8; ++i)
			abo.add(new Abonne());
	}

}
