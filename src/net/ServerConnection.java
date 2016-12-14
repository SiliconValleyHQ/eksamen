package net;

import java.net.Socket;

public class ServerConnection extends Thread {

	private CommunicationModule com;

	private CommunicationModule getCommunicationModule() {
		return com;
	}

	ServerConnection(Socket socket) {
		com = new CommunicationModule(socket);
		com.init();
	}

	boolean isConnected() {
		return getCommunicationModule().isConnected();
	}

	/**
	 * Facade method.
	 *
	 * @param message
	 */
	void say(String message) {
		getCommunicationModule().say(message);
	}

	String readLine() {
		return readLineBlocking();
	}

	private String readLineBlocking() {
		return getCommunicationModule().readLineBlocking();
	}

	@Override
	public void run() {

	}

}