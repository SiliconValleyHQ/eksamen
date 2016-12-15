package nettverk;

import java.net.Socket;

public class ServerKobling extends Thread {

	private KommunikasjonsModul com;

	ServerKobling(Socket socket) {
		com = new KommunikasjonsModul(socket);
		com.init();
	}

	private KommunikasjonsModul getCommunicationModule() {
		return com;
	}

	boolean erKoblet() {
		return getCommunicationModule().erKoblet();
	}

	/**
	 * Facade method.
	 *
	 * @param message
	 */
	void say(String message) {
		getCommunicationModule().melding(message);
	}

	String readLine() {
		return readLineBlocking();
	}

	private String readLineBlocking() {
		return getCommunicationModule().lesLinjeBlokkering();
	}

	@Override
	public void run() {

	}

}