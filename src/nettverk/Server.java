package nettverk;

/**
 * Denne klassen starter en ny tråd og initialiserer ServerSpill, som holder på informasjon om spillet
 */
public class Server extends Thread {

	private ServerSpill serverSpill;

	public Server(int port) {
		serverSpill = new ServerSpill(port);
	}

	@Override
	public void run() {
		serverSpill.start();
	}

}