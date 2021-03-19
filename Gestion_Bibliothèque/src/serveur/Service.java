package serveur;

import java.net.Socket;

import bibliotheque.Bibliotheque;

public abstract class Service implements Runnable{

	final Socket client; 
	final Bibliotheque bibli;

	Service(Socket socket, Bibliotheque b) {
		client = socket;
		bibli = b;
	}

	@Override
	public abstract void run();

	protected void finalize() throws Throwable {
		client.close(); 
	}

	public void launch() {
		new Thread(this).start();	
	}
	
}
