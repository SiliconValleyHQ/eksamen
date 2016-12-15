package nettverk;

public class Server extends Thread {

	private ServerSpill serverSpill;

	public Server(int port) {
		serverSpill = new ServerSpill(port);
	}

	@Override
	public void run() {
		serverSpill.start();
	}

}