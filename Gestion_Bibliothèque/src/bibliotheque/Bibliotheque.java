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
	 * @param numDoc : num�ro du doc � rendre
	 * @throws Exception
	 */
	public synchronized void retour(int numDoc) throws Exception {
		IDocument d = searchDoc(numDoc);
		d.retour();
	}

	/**
	 * Reserver un document
	 * @param numAb : numero d'abonn�
	 * @param numDoc : num�ro de doc
	 * @throws Exception si doc inexistant
	 */
	public synchronized void reserver(int numAb, int numDoc) throws Exception {
		if (verifNumAbo(numAb) == true) {
			IDocument d = searchDoc(numDoc);
			d.reserver(searchAbo(numAb));
			booked = false;
			wait(30000);
			if (booked == false) {
				System.err.println("D�lai de r�servation d�pass� !");
				d.retour();
			}
		}

		else System.err.println("Num�ro d'abonn� ou de document incorrect");
	}

	/**
	 * Emprunter un document
	 * @param numAb : numero d'abonn�
	 * @param numDoc : num�ro de doc
	 * @throws Exception si doc inexistant
	 */
	public synchronized void emprunter(int numAb, int numDoc) throws Exception {
		if (verifNumAbo(numAb) == true) {
			IDocument d = searchDoc(numDoc);
			d.emprunter(searchAbo(numAb));
			booked = true;
			notifyAll();
		}

		else System.err.println("Num�ro d'abonn� ou de document incorrect");
	}

	/**
	 * Verification du num d'abonn�
	 * @param num : le num�ro d'abonn�
	 * @return bol�en
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
	 * @return l'abonn� trouv� ou pas
	 * @throws Exception
	 */
	private IAbonne searchAbo(int num) throws Exception {
		IAbonne abo = null;

		for (IAbonne a : abonnes) {
			if (a.num() == num)
				abo = a;
		}

		if (abo == null) 
			throw new Exception("abonn� inexistant"); 

		return abo;
	}

	/**
	 * Initialisation des livres
	 * @param doc : liste de doc � remplir
	 */
	private void remplirDocs(List<IDocument> doc) {
		for (int i = 0; i < 8; ++i)
			doc.add(new Livre());
	}

	/** 
	 * Initialisation des abonn�s
	 * @param abo : liste d'abo � remplir
	 */
	private void remplirAbos(List<IAbonne> abo) {
		for (int i = 0; i < 8; ++i)
			abo.add(new Abonne());
	}

}
