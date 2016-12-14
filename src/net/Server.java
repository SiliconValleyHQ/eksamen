package net;

public class Server extends Thread {

	private ServerGame serverGame;

	public Server(int port) {
		serverGame = new ServerGame(port);
	}

	@Override
	public void run() {
		serverGame.start();
	}

}