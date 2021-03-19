package serveur;

import java.io.IOException;
import java.net.ServerSocket;

import bibliotheque.Bibliotheque;

class FabriqueService {

	public static Service fabriquerService(int numPort, ServerSocket sock, Bibliotheque b) {
		try {
			switch (numPort) {
				case 2500 :
					return new ServiceResa(sock.accept(), b);
				case 2600 :
					return new ServiceEmprunt(sock.accept(), b);
				case 2700 :
					return new ServiceRetour(sock.accept(), b);
				default :
					System.err.println("Numero de port non pris en charge !");
					break;
			}
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		return null;
	}
	
}
