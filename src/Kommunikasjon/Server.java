package Kommunikasjon;

import java.net.ServerSocket;

/**
 * Created by mariuswetterlin on 18.11.2016.
 */
public class Server {

	private ServerSocket sSocket;
	private Konfigurering konfig;
//	public static final int SERVER_TICK = 10; // Usikker på om denne trengs

	private Server(int port) {
		this.konfig = new Konfigurering(port);
	}

//	Usikker på om denne trengs
//	public static int getSTick() {
//		return SERVER_TICK;
//	}

	private void setSSocket(ServerSocket sSocket) {
		this.sSocket = sSocket;
	}

	private ServerSocket getSSocket() {
		return sSocket;
	}

	private void setKonfigurering(Konfigurering konfig) {
		this.konfig = konfig;
	}

	private Konfigurering getKonfigurering() {
		return konfig;
	}

}