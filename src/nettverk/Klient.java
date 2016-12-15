package nettverk;

import spill.Spill;
import spill.Trekk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Klient extends Thread {

	private ActionListener actionListener;
	private Spill spill;
	private KommunikasjonsModul kommunikasjonsModul;

	public Klient(int port) {
		try {
			lagKommunikasjonsModulPå(new Socket("localhost", port));
			kommunikasjonsModul.init();
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

	@Override
	public void run() {
		String melding;
		do {
			melding = getKommunikasjonsModul().getLinjeBlokkering();
			MeldingsProssesor meldingsProssesor = new MeldingsProssesor();
			meldingsProssesor.process(melding);
		} while (!"END".equals(melding));

	}

	public class MeldingsProssesor {

		MeldingsProssesor() {

		}

		void process(String melding) {
			if (null == melding) {
				throw new IllegalArgumentException("Melding kan ikke være null");
			}

			if (melding.equals("DIN TUR")) {
				getKommunikasjonsModul().melding("TREKK 1,1 2,1");
			}
			else if (melding.startsWith("MOTSTANDER TREKK ")) {
				//Received opponent trekk
				// Trekk received.
				Scanner scanner = new Scanner(melding);
				scanner.next();
				scanner.next();//skip words OPPONENT MOVE
				String from = scanner.next();
				String to = scanner.next();
				Trekk trekk = new Trekk(from, to);
				echo("Mottok motspiller trekk: " + trekk);
				//pass action to the actionlistener
				getActionListener().actionPerformed(
						new ActionEvent(
								this,
								1 /*doesn't matter for use let's just take 1*/,
								"REPAINT ON MOVE " + trekk.toString2()));
			}
			else if (melding.startsWith("YOU ARE PLAYING FOR")) {
				echo(melding);
			}
			else //noinspection StatementWithEmptyBody
				if (melding.startsWith("PLEASE OCCUPY SEATS")) {
					//do whatever
				}
				else if (melding.equals("WELCOME")) {
					//this is for illustration of different message passing usage
					//pass action to the actionlistener
					getActionListener().actionPerformed(
							new ActionEvent(
									this,
									1 /*doesn't matter for use let's just take 1*/,
									"GOT WELCOMING")
					);
				}
				else {
					echo(String.format("Don't know how to prosses message \"%s\"", melding));
					//another illustration
					//pass action to the actionlistener
					getActionListener().actionPerformed(
							new ActionEvent(this, 1 /*doesn't matter for use let's just take 1*/,
									"ERROR"));
				}
		}

		void echo(String message) {
			System.out.println(message);
		}

	}

}