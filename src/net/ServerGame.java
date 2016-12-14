package net;

import game.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class ServerGame extends Thread {

	private Game game;
	private ServerCommunicationModule communicationModule;
	private ServerConnection[] connections;
	private ServerSocket serverSocket;

	private Game getGame() {
		return game;
	}

	private ServerCommunicationModule getCommunicationModule() {
		return communicationModule;
	}

	private ServerConnection[] getConnections() {
		return connections;
	}

	private ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	ServerGame(int port) {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.err.println("Could not open server socket.");
			e.printStackTrace(System.err);
		}
	}

	@Override
	public void run() {
		while (!isConnected())
			waitForConnections();

		//Past this point connections are available. Start game.
		setGame(new Game());

		say("WELCOME");
		say("PLEASE OCCUPY SEATS");

		getGame().occupySeat(player1());
		getGame().occupySeat(player2());

		say(player1(), "YOU ARE PLAYING FOR " + getGame().seatName(player1()));
		say(player2(), "YOU ARE PLAYING FOR " + getGame().seatName(player2()));

		MessageProcessor processor = new MessageProcessor(this);

		ServerConnection winner = null;
		while (!winningcondition()) {
			say(player1(), "YOUR TURN");
			processor.process(readLine(player1()), player1());

			if (winningCondition()) //Check for winning condition. NOTE that there might be no possible move so then wins the one who moved last.
			{
				winner = player1();
				break;
			}

			say(player2(), "YOUR TURN");
			processor.process(readLine(player2()), player2());
			if (winningCondition()) {
				winner = player2();
				break;
			}
		}
		anounceWinner(winner);
	}

	private boolean winningcondition() {
		//Check if no checkers left or no move possible.
		return false;
	}

	private void anounceWinner(ServerConnection player) {
		say(opponentOf(player), "YOU LOOSE!");
		say(player, "YOU WIN!");
	}

	private boolean winningCondition() {
		return false;
	}

	private ServerConnection player(int i) {
		return getConnections()[i];
	}

	private ServerConnection player1() {
		return player(0);
	}

	private ServerConnection player2() {
		return opponentOf(player1());
	}

	public ServerConnection opponentOf(ServerConnection sc) {
		return getConnections()[0].equals(sc) ? getConnections()[1] : getConnections()[1];
	}

	private boolean isConnected() {
		return getConnections() != null
				&& getConnections().length == 2
				&& player1().isConnected()
				&& player2().isConnected();
	}

	private void waitForConnections() {
		try {
			connections = new ServerConnection[] {
							new ServerConnection(getServerSocket().accept()), //wait player1
							new ServerConnection(getServerSocket().accept()) //wait player2

			};
			setCommunicationModule(new ServerCommunicationModule(connections));
		} catch (IOException e) {
			System.err.println("Could not accept connections.");
			e.printStackTrace(System.err);
		}
	}

	void say(ServerConnection toPlayer, String aMessage) {
		getCommunicationModule().say(toPlayer, aMessage);
	}

	private void say(String message) {
		getCommunicationModule().say(message);
	}

	private String readLine(ServerConnection fromPlayer) {
		return getCommunicationModule().readLine(fromPlayer);
	}

	private void setCommunicationModule(ServerCommunicationModule communicationModule) {
		this.communicationModule = communicationModule;
	}

	private void setGame(Game game) {
		this.game = game;
	}

}