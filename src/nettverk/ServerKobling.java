package nettverk;

import java.net.Socket;

/**
 * Denne klassen kobler serveren opp til KommunikasjonsModulen.
 * På den måten kan den ta i mot informasjon fra klientene og sende
 * informasjonen ut igjen til begge klientene, slik at de er like.
 */
public class ServerKobling extends Thread {

	private KommunikasjonsModul kommunikasjonsModul;

	ServerKobling(Socket socket) {
		kommunikasjonsModul = new KommunikasjonsModul(socket);
		kommunikasjonsModul.init();
	}

	private KommunikasjonsModul getKommunikasjonsModul() {
		return kommunikasjonsModul;
	}//

	boolean erKoblet() {
		return getKommunikasjonsModul().erKoblet();
	}

	/**
	 * dette er en simplifisert metode fra et bibliotek
	 *
	 * @param melding sender en string med informasjon
	 */
	void say(String melding) {
		getKommunikasjonsModul().melding(melding);
	}

	String readLine() {
		return readLineBlocking();
	}

	private String readLineBlocking() {
		return getKommunikasjonsModul().lesLinjeBlokkering();
	}

	@Override
	public void run() {

	}

}