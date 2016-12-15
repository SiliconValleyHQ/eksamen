package nettverk;

import spill.Spill;
import spill.Trekk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Klient extends Thread {

	/**
	 * Dette er nettverks klientene. Det er her kommunikasjonen fra Server taes i mot, og behandles
	 * før gui.Klient kan oppdaterer.
	 */

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
			System.err.println("Kan ikke starte kommunikasjonsmodul på port: " + port);
			e.printStackTrace();
		}
	}

	public Spill getSpill() {
		return spill;
	}

	private void setSpill(Spill spill) {
		this.spill = spill;
	}

	public void addActionListener(ActionListener objekt) { //we will pass matrise here
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
			MeldingsProssesor meldingsProssesor = new MeldingsProssesor();
			meldingsProssesor.process(melding);
		} while (!"END".equals(melding));

	}

	/**
	 * MeldingsProssesoren er en klasse under Klientklassen. Den sender Strenger som skal starte en action hos motstander klientene eller
	 * i denne spillerens klinet.
	 */
	public class MeldingsProssesor {

		void process(String melding) {
			if (null == melding) {
				throw new IllegalArgumentException("Melding kan ikke være null");
			}

			//Her sender vi en melding om at det er din tur. Her sender vi da ett statisk trekk fra koordinaten 1,1 til 2,1
			if (melding.equals("DIN TUR")) {
				getKommunikasjonsModul().melding("TREKK 1,1 2,1");
			}

			//Her taes trekket fra motstanderen i mot, og printes ut igjen.
			else if (melding.startsWith("MOTSTANDER TREKK")) {
				// Trekk mottaes.
				Scanner scanner = new Scanner(melding);
				scanner.next();
				scanner.next();//her hopper vi over OPPONENT MOVE
				String from = scanner.next();
				String to = scanner.next();
				Trekk trekk = new Trekk(from, to);
				echo("Mottok motspiller trekk: " + trekk);
				//Her vil vi sende Stringen til actionlistneren som kan motta og utføre trekket.
				getActionListener().actionPerformed(new ActionEvent(this, 1 , "REPAINT ON MOVE " + trekk.toString2()));
			}
			// Her sier konsollen hvilke spiller du er
			else if (melding.startsWith("YOU ARE PLAYING FOR")) {
				echo(melding);
			}
			else
				if (melding.startsWith("PLEASE OCCUPY SEATS")) {
				}
				else if (melding.equals("WELCOME")) {
					// dette illustrerer bare en annen måte å sende en melding på
					getActionListener().actionPerformed(new ActionEvent(this, 1, "GOT WELCOMING"));
				}
				else {
					echo(String.format("Don't know how to prosses message \"%s\"", melding));
					// dette illustrerer bare en annen måte å sende en melding på
					getActionListener().actionPerformed(
							new ActionEvent(this,1,"ERROR"));
				}
		}

		void echo(String message) {
			System.out.println(message);
		}

	}

}