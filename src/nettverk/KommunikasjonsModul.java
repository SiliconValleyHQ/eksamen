package nettverk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Kommunikasjonsmodulen tar i mot og leser informasjonen som kommer fra Socket, den sender også.
 * Det er denne klassen som tar seg av serialisering. I den forstand at den sender og tar i mot meldinger
 */
public class KommunikasjonsModul {

	private Socket socket;
	private PrintWriter ut;
	private BufferedReader inn;

	KommunikasjonsModul(Socket socket) {
		this.socket = socket;
	}

	private Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	private PrintWriter getUt() {
		return ut;
	}

	private void setUt(PrintWriter ut) {
		this.ut = ut;
	}

	private BufferedReader getInn() {
		return inn;
	}

	private void setInn(BufferedReader inn) {
		this.inn = inn;
	}

	void init() {
		try {
			setUt(new PrintWriter(
					getSocket().getOutputStream(), true));
			setInn(new BufferedReader(
					new InputStreamReader(
							getSocket().getInputStream())));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String lesLinje() {
		String les = null;
		try {
			les = inn.readLine();
		} catch (IOException e) {
			System.err.println("Kunne ikke lese fra Socket." + e);
		}
		return les;
	}

	String lesLinjeBlokkering() {
		return getLinjeBlokkering();
	}

	void melding(String melding) {
		while (null == getUt()) {
			System.err.println("Ut er ikke startet enda!");
		}
		System.out.println("Melding:" + melding);
		getUt().println(melding);
	}

	boolean erKoblet() {
		return getSocket().isConnected();
	}

	String getLinjeBlokkering() {
		String string;
		while (null == getInn()) {
			System.out.println("Ikke initialisert ennå!");
		}
		while (null == (string = lesLinje())) {

		}
		System.out.println("les:" + string);
		return string;
	}

}