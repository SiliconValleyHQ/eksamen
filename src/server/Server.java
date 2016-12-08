package server;

import java.net.ServerSocket;
import java.util.Scanner;

/**
 * Created by mariuswetterlin on 18.11.2016.
 */

public class Server {

	/**
	 * Server() har hovedansvaret for det som skjer med serveren.
	 */
	public Server() {
		String ip = "127.0.0.1";
		int port = 22222;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Skriv inn ip-adressen");
		ip = scanner.nextLine(); //Henter inn info som er skrevet i konsoll.
		System.out.println("skriv inn en port");
		port = scanner.nextInt(); //Henter inn innskrevet portnr
		while (port < 1 || port > 66666) { //Sjekker om innskrevet portnr er ett gyldig portnr, og har en verdi mellom 1 og 66666
			System.out.println("Porten du skrev inn er ikke en gyldig port mellom 1 og 66666");
			port = scanner.nextInt();
		}
	}

	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(22223);
		System.out.println("Tic Tac Toe Server is Running");
		try {
			while (true) {
				Player playerX = new Player(serverSocket.accept(), 'X');
				Player playerO = new Player(serverSocket.accept(), 'O');
				playerX.setOpponent(playerO);
				playerO.setOpponent(playerX);
				game.currentPlayer = playerX;
				playerX.start();
				playerO.start();
			}
		} finally {
			serverSocket.close();
		}
	}

}