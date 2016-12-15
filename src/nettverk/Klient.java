package nettverk;

import spill.Spill;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

/**
 * Dette er nettverks klientene. Det er her kommunikasjonen fra Server taes i mot, og behandles
 * før gui.Klient kan oppdaterer.
 */
public class Klient extends Thread {

	private ActionListener actionListener;
	private Spill spill;
	private KommunikasjonsModul kommunikasjonsModul;

	//Denne klienten startes samtidig som serveren på samme port. Vårt tilfelle 22222
	public Klient(int port) {
		try {
			//starter ny Socket på localhost med port 22222 og instanserer kommunikasjonsmodul
			lagKommunikasjonsModulPå(new Socket("localhost", port));
			kommunikasjonsModul.init();
			//Her henter vi inn klassen Spill som holder styr på statusen på spillet.
			setSpill(new Spill());
		} catch (IOException e) {
			System.err.println("Can not create communication module on port " + port);
			e.printStackTrace();
		}
	}

	public Spill getSpill() {
		return spill;
	}

	private void setSpill(Spill spill) {
		this.spill = spill;
	}

	public void addActionListener(ActionListener objekt) { //Her sender vi Matrisen
		actionListener = objekt;
	}

	private ActionListener getActionListener() {
		return actionListener;
	}

	private KommunikasjonsModul getKommunikasjonsModul() {
		return kommunikasjonsModul;
	}

	private void setKommunikasjonsModul(KommunikasjonsModul kommunikasjonsModul) {
		this.kommunikasjonsModul = kommunikasjonsModul;
	}

	private void lagKommunikasjonsModulPå(Socket socket) {
		setKommunikasjonsModul(new KommunikasjonsModul(socket));
	}

	/**
	 * I run metoden skjer det vil vil skal skje når vi starter en ny tråd.
	 * Her vil vi sende en melding som inneholder hva som skjer i spillet, som
	 * sendes til den andre klienten. Problemet vårt her at vi har flere tråder, og denne tråden kan jobbe raskere enn
	 * en GUI tråd. Disse vil da komme asynk, og skape problemer.
	 */
	@Override
	public void run() {
		String melding;
		do {
			melding = getKommunikasjonsModul().getLinjeBlokkering();
			MeldingsProssesor meldingsProssesor = new MeldingsProssesor(this);
			meldingsProssesor.prosses(melding, spiller1());
		} while (!"END".equals(melding));

	}

	private ServerKobling spiller1() {
		return null;
	}

}