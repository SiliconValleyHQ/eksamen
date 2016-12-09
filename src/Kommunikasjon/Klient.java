package Kommunikasjon;

import java.io.IOException;

/**
 * Created by mariuswetterlin on 08.12.2016.
 */
public class Klient {

	private Konfigurering konfig;

	private Klient(String ip, int port) throws IOException {
		this.konfig = new Konfigurering(ip, port);
	}

	private void setKonfigurering(Konfigurering konfig) {
		this.konfig = konfig;
	}

	private Konfigurering getKonfigurering() {
		return konfig;
	}

}