package serveur;
import java.io.IOException;
import java.net.ServerSocket;

import bibliotheque.Bibliotheque;

public class Serveur implements Runnable {

	private ServerSocket myServ;
	private int port;
	private Bibliotheque bibli;

	public Serveur(int numPort,  Bibliotheque b) throws IOException {
		myServ = new ServerSocket(numPort);
		port = numPort;
		bibli = b;
	}

	public ServerSocket getServ() {
		return myServ;
	}
	
	@Override
	public void run() {
		while (true) 
			FabriqueService.fabriquerService(port, myServ, bibli).launch();
	}

	protected void finalize() throws Throwable {
		this.myServ.close();
	}

}
