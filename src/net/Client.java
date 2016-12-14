package net;

import game.Game;
import game.Move;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {

	private Game game;
	private CommunicationModule communicationModule;
	ActionListener actionListener;

	public Game getGame() {
		return game;
	}

	public void addActionListener(ActionListener object)//we will pass grid here
	{
		actionListener = object;
	}

	public ActionListener getActionListener()
	{
		return actionListener;
	}

	private void setGame(Game game) {
		this.game = game;
	}

	public Client(int port) {
		try {
			createCommunicationModuleOn(new Socket("localhost", port));
			communicationModule.init();
			setGame(new Game());
		} catch (IOException e) {
			System.err.println("Can not create communication module on port " + port);
			e.printStackTrace();
		}
	}

	private CommunicationModule getCommunicationModule() {
		return communicationModule;
	}

	private void createCommunicationModuleOn(Socket socket) {
		setCommunicationModule(new CommunicationModule(socket));
	}

	@Override
	public void run() {
		String message;
		do {
			message = getCommunicationModule().getLineBlocking();
			MessageProcessor messageProcessor = new MessageProcessor();
			messageProcessor.process(message);
		}
		while (!"END".equals(message));

	}

	private void setCommunicationModule(CommunicationModule communicationModule) {
		this.communicationModule = communicationModule;
	}

	public class MessageProcessor {
		MessageProcessor() {

		}

		void process(String message) {
			if (null == message) {
				throw new IllegalArgumentException("Messsage cannot be null");
			}


			if (message.equals("YOUR TURN")) {
				getCommunicationModule().say("MOVE 1,1 2,1");
			}
			else if (message.startsWith("OPPONENT MOVE ")) {
				//Received opponent move
				// Move received.
				Scanner scanner = new Scanner(message);
				scanner.next();
				scanner.next();//skip words OPPONENT MOVE
				String from = scanner.next();
				String to = scanner.next();
				Move move = new Move(from, to);
				echo("Received opponent move: " + move);
				//pass action to the actionlistener
				getActionListener().actionPerformed(
						new ActionEvent(
								this,
								1 /*doesn't matter for use let's just take 1*/,
								"REPAINT ON MOVE " + move.toString2() )
				);
			}
			else if (message.startsWith("YOU ARE PLAYING FOR")) {
				echo(message);
			}
			else if (message.startsWith("PLEASE OCCUPY SEATS")) {
				//do whatever
			}
			else if (message.equals("WELCOME")) {
				//this is for illustration of different message passing usage
				//pass action to the actionlistener
				getActionListener().actionPerformed(
						new ActionEvent(
								this,
								1 /*doesn't matter for use let's just take 1*/,
								"GOT WELCOMING" )
				);
			}
			else {
				echo(String.format("Don't know how to process message \"%s\"", message));
				//another illustration
				//pass action to the actionlistener
				getActionListener().actionPerformed(
						new ActionEvent(this, 1 /*doesn't matter for use let's just take 1*/,
								"ERROR")
				);
			}
		}

		void echo(String message) {
			System.out.println(message);
		}
	}

}