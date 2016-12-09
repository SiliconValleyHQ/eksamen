package Kommunikasjon;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by mariuswetterlin on 08.12.2016.
 */
public class KommunikasjonsModul {

	private Socket s;
	private PrintWriter ut;
	private BufferedReader inn;

	private KommunikasjonsModul(Socket s) {
		this.s = s;
	}

	private void setSocket(Socket s) {
		this.s = s;
	}

	private Socket getSocket() {
		return s;
	}

	private void setUt(PrintWriter ut) {
		this.ut = ut;
	}

	private PrintWriter getUt() {
		return ut;
	}

	private void setInn(BufferedReader inn) {
		this.inn = inn;
	}

	private BufferedReader getInn() {
		return inn;
	}

}