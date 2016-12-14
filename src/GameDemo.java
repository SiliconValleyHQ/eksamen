import net.*;

public class GameDemo {

	private static final int PORT = 50001;

	public static void main(String[] args) {
		Server server = new Server(PORT);
		Client client1 = new Client(PORT);
		Client client2 = new Client(PORT);

		server.start();
		client1.start();
		client2.start();
	}

}