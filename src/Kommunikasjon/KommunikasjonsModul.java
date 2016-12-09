package Kommunikasjon;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Bror on 08.12.2016.
 * A class to hold Kommunikasjon game information.
 */
public class KommunikasjonsModul {

    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;

    public KommunikasjonsModul(Socket socket) {
        this.socket = socket;
    }

}
