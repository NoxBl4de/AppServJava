package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


class AppClient {

	private static final int PORT_RESA = 2500;
	private static final int PORT_EMPRUNT = 2600;
	private static final int PORT_RETOUR = 2700;
	private static final String HOST = "localhost";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket socket = null;
		try {
			
			final int PORT = 2600;
//			PORT = 2500;
//			PORT = 2700;
			
			switch(PORT) {
				case 2500:
					socket = new Socket(HOST, PORT_RESA);
					System.out.println("**** BIENVENUE SUR LA PLATEFORME DE RESERVATION ****");
					break;
				case 2600:
					socket = new Socket(HOST, PORT_EMPRUNT);
					System.out.println("**** BIENVENUE SUR LA PLATEFORME D'EMPRUNT ****");
					break;
				case 2700:
					socket = new Socket(HOST, PORT_RETOUR);
					System.out.println("**** BIENVENUE SUR LA PLATEFORME DE RETOUR ****");
					break;
				default:
					break;
			}
			
			BufferedReader sin = new BufferedReader (new InputStreamReader(socket.getInputStream()));
			PrintWriter sout = new PrintWriter(socket.getOutputStream(), true);

			BufferedReader clavier;// intialisé plus tard

			System.out.println("Connecté au serveur : " + socket.getInetAddress() + " || Port : " + 
					socket.getPort());

			String nums;
			String tmp;
			
			System.out.println("La bibliothèque répertorie 8 documents et 8 abonnés (donc numéros de 1 à 8 compris)");
			
			if (socket.getPort() == 2500 || socket.getPort() == 2600) {
				System.out.println("Entrez votre numéro d'abonné puis de document");
				System.out.println("Sous cette forme : numAb numDoc (Exemple : 7 8)");
			}
			
			else System.out.println("Entrez le numéro du document à rendre (Exemple : 1)");
			
			do {
				clavier = new BufferedReader(new InputStreamReader(System.in));
				
				nums = clavier.readLine(); // on stocke la saisie clavier
				
				sout.println(nums); // on envoie l'information au service

				String msg =sin.readLine(); // on stock le message envoyé par le service
				
				tmp = msg;
				
				System.out.println(msg);
				
				// permet de ne pas couper la connexion si une erreur d'entrée est commise
			} while (tmp.equals("Action réalisée avec succès !") == false); 

			socket.close(); // on ferme la socket avant de quitter

		} 
		catch (IOException e1){
			System.err.println(e1);
		}

		try {
			if (socket != null) socket.close();
		} catch (IOException e) {}
	}

}
