package net;

import game.Move;

import java.util.Scanner;

public class MessageProcessor {

	public ServerGame getSg() {
		return sg;
	}

	public void setSg(ServerGame sg) {
		this.sg = sg;
	}

	ServerGame sg;

	MessageProcessor(ServerGame sg) {
		setSg(sg);
	}

	void process(String message, ServerConnection player) {
		if (message == null) {
			throw new IllegalArgumentException("Message cannot be null.");
		}

		if (message.startsWith("MOVE ")) {
			// Move received.
			Scanner scanner = new Scanner(message);
			scanner.next();//skip word MOVE
			String from = scanner.next();
			String to = scanner.next();
			Move move = new Move(from, to);
			System.out.println("Received client move: " + move);

			//Pass this move to opponent
			getSg().say(getSg().opponentOf(player),"OPPONENT MOVE " + move.toString2()); //but i dont know how to fix oppoentOf without nullPointerException..
		} else {
			echo(String.format("Don't know how to process message %s", message));
		}
	}

	void echo(String message) {
		System.out.println(String.format("Processing '%s'", message));
	}

}