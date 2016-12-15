package nettverk;

class ServerKommunikasjonsModul {

	private ServerKobling[] kobling;

	ServerKommunikasjonsModul(ServerKobling[] kobling) {
		this.kobling = kobling;
	}

	private ServerKobling[] getKobling() {
		return kobling;
	}

	/**
	 * Say to everyone.
	 *
	 * @param melding
	 */
	void say(String melding) {
		for (ServerKobling kobling : getKobling()) {
			kobling.say(melding);
		}
	}

	/**
	 * Say to specific communication module.
	 *
	 * @param kobling
	 * @param melding
	 */
	void say(ServerKobling kobling, String melding) {
		kobling.say(melding);
	}

	String lesLinje(ServerKobling kobling) {
		return kobling.readLine();
	}

}