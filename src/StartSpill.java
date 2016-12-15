import nettverk.Klient;
import nettverk.Server;

/**
 * Åpner en server og 2 klienter på port 22222
 */
public class StartSpill {

	/**
	 * Setter porten som server og nettverk/klientene som trådene kjører på.
	 */
	private static final int PORT = 22222;

	public static void main(String[] args) {
		Server server = new Server(PORT);
		Klient klient1 = new Klient(PORT);
		Klient klient2 = new Klient(PORT);

		server.start();
		klient1.start();
		klient2.start();
	}

}