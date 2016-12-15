package nettverk;

import spill.Trekk;
import java.util.Scanner;

class MeldingsProssesor {

	private ServerSpill serverSpill;

	MeldingsProssesor(ServerSpill serverSpill) {
		setServerSpill(serverSpill);
	}

	private ServerSpill getServerSpill() {
		return serverSpill;
	}

	private void setServerSpill(ServerSpill serverSpill) {
		this.serverSpill = serverSpill;
	}

	void prosses(String melding, ServerKobling spiller) {
		if (melding == null) {
			throw new IllegalArgumentException("Melding kan ikke være null.");
		}

		if (melding.startsWith("MOVE ")) {
			// Trekk received.
			Scanner scanner = new Scanner(melding);
			scanner.next(); //skip word MOVE
			String fra = scanner.next();
			String til = scanner.next();
			Trekk trekk = new Trekk(fra, til);
			System.out.println("Mottok klient trekk: " + trekk);

			//Pass this trekk to opponent
			getServerSpill().say(getServerSpill().motstanderAv(spiller), "OPPONENT MOVE " + trekk.toString2()); //but i dont know how to fix oppoentOf without nullPointerException..
		}
		else {
			echo(String.format("Vet ikke hvordan man håndterer melding %s", melding));
		}
	}

	private void echo(String melding) {
		System.out.println(String.format("Prosseserer '%s'", melding));
	}

}