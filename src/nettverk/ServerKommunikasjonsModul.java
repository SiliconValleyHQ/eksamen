package nettverk;

/**
 * Denne klassen skal sende ut informasjon til alle klientene som er koblet til.
 * Dette vil jo da kunne sørge for at klientene er oppdatert med samme informasjon.
 */
class ServerKommunikasjonsModul {

	private ServerKobling[] kobling;

	ServerKommunikasjonsModul(ServerKobling[] kobling) {
		this.kobling = kobling;
	}

	private ServerKobling[] getKobling() {
		return kobling;
	}

	/**
	 * Dette er meldinger som skal sendes til alle klientene
	 */
	void say(String melding) {
		for (ServerKobling kobling : getKobling()) {
			kobling.say(melding);
		}
	}

	/**
	 * Denne snakker med en spesifikk kommunikasjonsmodul.
	 */
	void say(ServerKobling kobling, String melding) {
		kobling.say(melding);
	}

	String lesLinje(ServerKobling kobling) {
		return kobling.readLine();
	}

}