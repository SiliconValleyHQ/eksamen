package spillLogikk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Bror on 07.12.2016.
 */
public class Spiller extends Thread {

    char mark;
    Spiller motstander;
    Socket socket;
    BufferedReader input;
    PrintWriter output;

    public Spiller(Socket socket, char mark) {
        this.socket = socket;
        this.mark = mark;

        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(),true);
            System.out.println("Velkommen " + mark);
        } catch (IOException e) {
            System.out.println("Tror motstanderen døde?..." + e);
        }
    }

}
