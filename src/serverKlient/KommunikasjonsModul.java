package serverKlient;

import spill.DamSpill;

import java.io.*;
import java.net.Socket;

/**
 * Created by Bror on 08.12.2016.
 * Klasse for å holde på spill informasjon
 */
public class KommunikasjonsModul implements Runnable {

	private Socket socket;
	private PrintWriter output;
	private BufferedReader input;
	private boolean spiller1tur = false;
	private boolean spiller2tur = false;

	protected KommunikasjonsModul(Socket klientSocket) throws IOException {
		spiller1tur = true; //Sørger for at spiller 1 får første trekk
		this.socket = klientSocket;

		//Dette skal i teori kunne sende spillet fra en klient til en annen.
		DamSpill spillBrett = new DamSpill();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(klientSocket.getOutputStream());
		objectOutputStream.writeObject(spillBrett);

		objectOutputStream.close();
	}

	private void utfortTrekk() throws Exception {
		spiller1tur = !spiller1tur;
		spiller2tur = !spiller2tur;

		sendBrett();
	}

	private void taImotBrett() {

	}

	private void sendBrett() throws Exception {
		Socket socket;
		/*ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
            socket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }

        taImotBrett();*/

		socket = new Socket();
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	}

	private boolean isSpiller1tur() {
		return spiller1tur;
	}

	private boolean isSpiller2tur() {
		return spiller2tur;
	}

	@Override
	public void run() {

	}

}