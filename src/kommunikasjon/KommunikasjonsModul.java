package kommunikasjon;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Bror on 08.12.2016.
 * A class to hold kommunikasjon game information.
 */
public class KommunikasjonsModul implements Runnable {

    Socket socket;
    private PrintWriter output;
    private BufferedReader input;

    public KommunikasjonsModul(Socket klientSocket) {
        this.socket = klientSocket;
        System.out.println("hallo?");
    }

    @Override
    public void run() {

    }
}
