package nettverk;

import spill.Trekk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

/**
 * MeldingsProssesoren er en klasse som tar i mot melinger og sender meldinger som blir tolket av en ActionListener.
 * Den sender Strenger som skal starte en action hos motstander klientene eller i denne spillerens klinet.
 */
public class MeldingsProssesor {

	private KommunikasjonsModul kommunikasjonsModul;

	MeldingsProssesor(Klient serverSpill) {

	}

	MeldingsProssesor(ServerSpill serverSpill) {

	}

	void prosses(String melding, ServerKobling serverKobling) {
		if (null == melding) {
			throw new IllegalArgumentException("Melding kan ikke være null");
		}
		//Her sender vi en melding om at det er din tur. Her sender vi da ett statisk trekk fra koordinaten 1,1 til 2,1
		if (melding.equals("DIN TUR")) {
			getKommunikasjonsModul().melding("TREKK 1,1 2,1");
		}
		//Her taes trekket fra motstanderen i mot, og printes ut igjen.
		else if (melding.startsWith("MOTSTANDER TREKK ")) {
			// Trekk mottaes.
			Scanner scanner = new Scanner(melding);
			scanner.next();
			scanner.next();//her hopper vi over OPPONENT MOVE
			String from = scanner.next();
			String to = scanner.next();
			Trekk trekk = new Trekk(from, to);
			echo("Mottok motspiller trekk: " + trekk);
			//Her vil vi sende Stringen til actionlistneren som kan motta og utføre trekket.
			getActionListener().actionPerformed(
					new ActionEvent(
							this,
							1 /*doesn't matter for use let's just take 1*/,
							"REPAINT ON MOVE " + trekk.toString2()));
		}
		// Her sier konsollen hvilke spiller du er
		else if (melding.startsWith("YOU ARE PLAYING FOR")) {
			echo(melding);
		}
		else if (melding.startsWith("PLEASE OCCUPY SEATS")) {

			}
			else if (melding.equals("WELCOME")) {
			// dette illustrerer bare en annen måte å sende en melding på
				getActionListener().actionPerformed(new ActionEvent(this, 1,"GOT WELCOMING"));
			}
			else {
				echo(String.format("Don't know how to prosses message \"%s\"", melding));
				// dette illustrerer bare en annen måte å sende en melding på
				getActionListener().actionPerformed(
						new ActionEvent(this, 1,"ERROR"));
			}
	}

	void echo(String message) {
		System.out.println(message);
	}

	public ActionListener getActionListener() {
		ActionListener actionListener = null;
		return actionListener;
	}

	public KommunikasjonsModul getKommunikasjonsModul() {
		return kommunikasjonsModul;
	}
}