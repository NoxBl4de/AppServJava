package serveur;

import java.io.IOException;

import bibliotheque.Bibliotheque;

class AppServ {

	private static final int RESERVATION = 2500;
	private static final int EMPRUNT = 2600;
	private static final int RETOUR = 2700;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		final Bibliotheque bib = new Bibliotheque();
		new Thread(new Serveur(RESERVATION, bib)).start();
		new Thread(new Serveur(EMPRUNT, bib)).start();
		new Thread(new Serveur(RETOUR, bib)).start();
		System.out.println("Serveur lancé sur le port : " + RESERVATION);
		System.out.println("Serveur lancé sur le port : " + EMPRUNT);
		System.out.println("Serveur lancé sur le port : " + RETOUR);
	}


}
