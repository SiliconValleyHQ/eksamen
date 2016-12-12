package serverKlient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Bror on 18.11.2016.
 * Server klassen blir staret av en av spillerene. Brukeren taster inn hvilke port en ny ServerSocket
 * skal startes på.
 */
public class Server {

	// Lagrer input fra konsoll i en variabel
	private Scanner scanner = new Scanner(System.in);

	// Definerer thread
	private Thread thread;

	//Definerer ServerSocket
	private ServerSocket serverSocket;

	//Standard porten serveren startes på
	private int port = 22222;

	/**
	 * Konstruktør som ikke tar i mot noen argumenter. Den starter kun metoden nyServerSocket();
	 */
	private Server() {
		nyServerSocket();
	}

	/**
	 * Denne metoden har ansvar for å starte en ny serversocket. Dette gjøres ved at brukeren taster inn
	 * ønsket port.
	 */
	private void nyServerSocket() {
		System.out.println("skriv inn porten du ønsker serveren skal være på");

		// Port henter sin nye verdi fra scanner, som leser en int fra konsollen.
		port = scanner.nextInt(); //Henter inn innskrevet portnr
		try {
			//starter en ny serverSocket av typen serversocket på porten angitt av brukeren
			serverSocket = new ServerSocket(port);
			thread = new Thread();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("nope, kan ikke lage ServerSocket");
		}
		// Serveren kaller nå på metoden klientLytter
		klientLytter();
	}

	/**
	 * Serveren går nå i lyttemodus. Her vil metoden blokke programmet til
	 * serveren får forbindelse med en klient
	 */
	private void klientLytter() {
		try {
			Socket socket;
			System.out.println("Venter på klienter...");
			// While loopen kommer til å blokke programmet med serverSocket.accept() til serveren har funnet en klient
			while ((socket = serverSocket.accept()) != null) {
				// når serveren har forbindelse med en klient, vil serveren kalle på en ny kommunikasjonsmodul med socket som argument
				new KommunikasjonsModul(socket);
				System.out.println("Ny spiller koblet til!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * main som instanserer og kjører i gang serveren. Denne har mulighet til å ta i mot argumenter
	 * men det har vi ikke gjort, da det ikke er nødvendig
	 *
	 * @param args
	 * 		foreløpig ikke i bruk, men dette er navnet på argumenter som kommer inn fra run configurations
	 */
	public static void main(String[] args) {
		new Server();
	}

}