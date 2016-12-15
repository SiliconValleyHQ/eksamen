package nettverk;

import spill.Spill;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Denne klassen holder på informasjon om spillet og statusene det kan ha.
 * Den sjekker om noen er koblet til, hvilke lag de er.
 * Den jobber med informasjonen den sender og mottar gjennom kommunikasjonsmodulen.
 * og holder sin egen ServerSocket.
 * Klassen sjekker også om noen har vunnet, og annonserer det.
 */
public class ServerSpill extends Thread {

	private Spill spill;
	private ServerKommunikasjonsModul kommunikasjonsModul;
	private ServerKobling[] kobling;
	private ServerSocket serverSocket;

	private Spill getSpill() {
		return spill;
	}

	private ServerKommunikasjonsModul getKommunikasjonsModul() {
		return kommunikasjonsModul;
	}

	private ServerKobling[] getKobling() {
		return kobling;
	}

	private ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	ServerSpill(int port) {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.err.println("Kunne ikke åpne server socket.");
			e.printStackTrace(System.err);
		}
	}

	@Override
	public void run() {
		while (!erKoblet())
			ventForKobling();

		//Her er er en koblig satt opp mellom to nettverk.klienter og spillet kan starte.
		setSpill(new Spill());

		say("VELKOMMEN");

		getSpill().okkuperPlass(spiller1());
		getSpill().okkuperPlass(spiller2());

		say(spiller1(), "DU SPILLER FOR " + getSpill().plassPosisjon(spiller1()));
		say(spiller2(), "DU SPILLER FOR " + getSpill().plassPosisjon(spiller2()));

		MeldingsProssesor prossesor = new MeldingsProssesor(this);

		ServerKobling vinner = null;
		while (!vinnerStatus()) {
			say(spiller1(), "DIN TUR");
			prossesor.prosses(readLine(spiller1()), spiller1());

			// Sjekker om noen har vunnet.
			if (vinnerStatus()) {
				vinner = spiller1();
				break;
			}

			say(spiller2(), "DIN TUR");
			prossesor.prosses(readLine(spiller2()), spiller2());
			if (vinnerStatus()) {
				vinner = spiller2();
				break;
			}
		}
		anounceWinner(vinner);
	}

	private boolean vinnerStatus() {
		//TODO Sjekk etter lovlige trekk, eller om det ikke er flere brikker.
		return false;
	}

	private void anounceWinner(ServerKobling spiller) {
		say(motstanderAv(spiller), "DU TAPER!");
		say(spiller, "DU VINNER!");
	}

	private ServerKobling spiller(int i) {
		return getKobling()[i];
	}

	private ServerKobling spiller1() {
		return spiller(0);
	}

	private ServerKobling spiller2() {
		return motstanderAv(spiller1());
	}

	ServerKobling motstanderAv(ServerKobling sc) {
		return getKobling()[0].equals(sc) ? getKobling()[1] : getKobling()[1];
	}

	private boolean erKoblet() {
		return getKobling() != null
				&& getKobling().length == 2
				&& spiller1().erKoblet()
				&& spiller2().erKoblet();
	}

	private void ventForKobling() {
		try {
			kobling = new ServerKobling[]{
					new ServerKobling(getServerSocket().accept()), //venter på spiller1
					new ServerKobling(getServerSocket().accept())}; //venter på spiller2
			setKommunikasjonsModul(new ServerKommunikasjonsModul(kobling));
		} catch (IOException e) {
			System.err.println("Kunne ikke godta kobling.");
			e.printStackTrace(System.err);
		}
	}

	void say(ServerKobling tilSpiller, String aMelding) {
		getKommunikasjonsModul().say(tilSpiller, aMelding);
	}

	private void say(String melding) {
		getKommunikasjonsModul().say(melding);
	}

	private String readLine(ServerKobling fraSpiller) {
		return getKommunikasjonsModul().lesLinje(fraSpiller);
	}

	private void setKommunikasjonsModul(ServerKommunikasjonsModul kommunikasjonsModul) {
		this.kommunikasjonsModul = kommunikasjonsModul;
	}

	private void setSpill(Spill spill) {
		this.spill = spill;
	}

}