package net;

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;

public class CommunicationModule {

	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;

	CommunicationModule(Socket socket) {
		this.socket = socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	private Socket getSocket() {
		return socket;
	}

	private void setOut(PrintWriter out) {
		this.out = out;
	}

	private PrintWriter getOut() {
		return out;
	}

	private void setIn(BufferedReader in) {
		this.in = in;
	}

	private BufferedReader getIn() {
		return in;
	}

	void init() {
		try {
			setOut(new PrintWriter(
					getSocket().getOutputStream(), true));
			setIn(new BufferedReader(
					new InputStreamReader(
							getSocket().getInputStream())));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String readLine() {
		String ret = null;
		try {
			ret = in.readLine();
		} catch (IOException e) {
			System.err.println("Could not read from socket." + e);
		}
		return ret;
	}

	String readLineBlocking() {
		return getLineBlocking();
	}

	void say(String msg) {
		while (null == getOut()) {
			System.err.println("Out not initialized yet!");
		}
		System.out.println("said:" + msg);
		getOut().println(msg);
	}

	boolean isConnected() {
		return getSocket().isConnected();
	}

	String getLineBlocking() {
		String str;
		while (null == getIn()) {
			System.out.println("In not initialized yet!");
		}
		while (null == (str = readLine())) {

		}
		System.out.println("read:" + str);
		return str;
	}

}