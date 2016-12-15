import nettverk.Klient;
import nettverk.Server;

public class SpillDemo {

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