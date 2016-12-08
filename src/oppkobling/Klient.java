package oppkobling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Bror on 08.12.2016.
 */
public class Klient {

    private PrintWriter output;
    private BufferedReader input;
    private Socket socket;

    private int port = 22222;

    public Klient(String serverAdresse)  {
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main (String[] args) {
        while (true) {
            String serverAdresse = (args.length == 0) ? "127.0.0.1" : args[1];
            Klient klient = new Klient(serverAdresse);
        }
    }

}
