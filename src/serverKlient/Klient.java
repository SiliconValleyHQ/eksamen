package serverKlient;

import spill.DamSpill;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Bror on 08.12.2016.
 * Klassen Klient inneholder den viktigste informasjonen en basis klient må inneholde
 */
public class Klient implements Runnable {

	KommunikasjonsModul com = null;

	/** Standard portnummer, om brukerne ikke har spesifisert noe annet */
	private int port = 22222;

	/** Standard IP-adresse om brukeren ikke har spesifisert noe annet */
	private String ip = "127.0.0.1";

	/** Definerer Socket  */
	private Socket socket;

	/** Definerer DataOutputStream */
	private DataOutputStream dos;

	/** Defeinerer DataInputStream */
	private DataInputStream dis;

	private Thread thread;

	/** Tar og lagrer input fra konsollen til brukeren i variabelen scanner */
	private Scanner scanner = new Scanner(System.in);

	private Klient() throws IOException, ClassNotFoundException {
		forbindelse();
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		new Klient();
	}

	private boolean forbindelse() throws ClassNotFoundException {
		System.out.println("boooom");
		try {
			System.out.println("Skriv inn ip-adressen til serveren");
			ip = scanner.nextLine(); //Henter inn info som er skrevet i konsoll.
			System.out.println("skriv inn en porten til serveren");
			port = scanner.nextInt(); //Henter inn innskrevet portnr
			socket = new Socket(ip, port);//this is what kommunikasjons module has to get to "speak" and "listen"

			com = new KommunikasjonsModul(socket);//from this moment klient can speak and listen through the socket. The plan is to speak in strings. Say con.say("MOVE A1 B1"); aand listen for such messages. Let's proceed to listening and parsing part.

			dos = new DataOutputStream(socket.getOutputStream());

			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			System.out.println("Jeg kjører");
			DamSpill spillStatus = (DamSpill) objectInputStream.readObject();
			spillStatus.LagOgVisGUI();

			dis = new DataInputStream(socket.getInputStream());
			System.out.println("greaaaaat success?");
			thread = new Thread();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private DataInputStream getDataInputStream() {
		return dis;
	}

	private DataOutputStream getDataOutputStream() {
		return dos;
	}

	@Override
	public void run() {
		try {
			new KommunikasjonsModul(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}