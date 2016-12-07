package spillLogikk;

import java.io.BufferedReader;
import java.io.IOException;
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

        } catch (IOException e) {

        }
    }

}
