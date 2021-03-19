package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import bibliotheque.Bibliotheque;

public class ServiceRetour extends Service {

	public ServiceRetour(Socket socket, Bibliotheque b) {
		super(socket, b);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("***** Une nouvelle connexion a d�marr� sur le port de retour ! " + 
				client.getInetAddress() + " *****");

		try {
				BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream()));
				PrintWriter out = new PrintWriter (client.getOutputStream(), true);
				String line = in.readLine(); // on recup�re l'info envoy� par le serveur
				String msg = "Action r�alis�e avec succ�s !";

				int numDoc = Integer.parseInt(line);

				bibli.retour(numDoc);

				out.println(msg);
			
		} catch (Exception e) {e.printStackTrace();}

		System.out.println("***** Connexion termin�e *****");
		try {
			client.close();
		} catch (IOException e2) {}
	}
	
}
