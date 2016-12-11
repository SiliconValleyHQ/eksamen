package kommunikasjon;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Bror on 08.12.2016.
 * Klasse for å holde på spill informasjon
 */
public class KommunikasjonsModul implements Runnable {

    Socket socket;
    private PrintWriter output;
    private BufferedReader input;
    boolean spiller1tur = false;
    boolean spiller2tur = false;

    public KommunikasjonsModul(Socket klientSocket) {
        spiller1tur = true; //Sørger for at spiller 1 får første trekk
        this.socket = klientSocket;
        System.out.println("hallo?");
    }

    public void utfortTrekk() {
        spiller1tur = !spiller1tur;
        spiller2tur = !spiller2tur;
    }

    public boolean isSpiller1tur() {
        return spiller1tur;
    }

    public boolean isSpiller2tur() {
        return spiller2tur;
    }

    @Override
    public void run() {

    }
}
