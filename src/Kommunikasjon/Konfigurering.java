package Kommunikasjon;

/**
 * Created by mariuswetterlin on 08.12.2016.
 */
public class Konfigurering {

	private String ip = "Localhost";
	private int port = 22222;

	Konfigurering(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	Konfigurering(int port) {
		this.port = port;
	}

	private void setIp(String ip) {
		this.ip = ip;
		System.out.println(ip);
	}

	private String getIp() {
		return ip;
	}

	private void setPort(int port) {
		this.port = port;
		System.out.println(port);
	}

	private int getPort() {
		return port;
	}

}