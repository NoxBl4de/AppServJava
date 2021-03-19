package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import bibliotheque.Bibliotheque;

public class ServiceEmprunt extends Service {

	ServiceEmprunt(Socket socket, Bibliotheque b) {
		super(socket, b);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("***** Une nouvelle connexion a d�marr� sur le port d'emprunt ! " + 
				client.getInetAddress() + " *****");

		boolean err = false;

		try {
			do  {
				BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream()));
				PrintWriter out = new PrintWriter (client.getOutputStream(), true);
				String line = in.readLine(); // on recup�re l'info envoy� par le serveur
				String msg = "Action r�alis�e avec succ�s !";
				String[] lines = line.split(" "); // division de la ligne pour exploitation des donn�es

				int numAb = Integer.parseInt(lines[0]);
				int numDoc = Integer.parseInt(lines[1]);

				bibli.emprunter(numAb, numDoc);

				// verification permettant de savoir quel message renvoyer
				if (bibli.verifNumAbo(numAb)) {
					out.println(msg);
					err = false;
				}
				else {
					out.println("Erreur dans le num�ro d'abonn� ou de document. Veuillez r�essayer :");
					err = true;
				}
				// permet de recommencer s'il y a une erreur
			} while (err == true);
			
		} catch (Exception e) {e.printStackTrace();}

		System.out.println("***** Connexion termin�e *****");
		try {
			client.close();
		} catch (IOException e2) {}

	}


}
