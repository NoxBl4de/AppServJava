package document;

public class PasLibreException extends Exception {

	private static final long serialVersionUID = 1L;

	public PasLibreException() {
		System.out.println("Le livre que vous essayez d'emprunter n'est pas libre !");
	}
}
