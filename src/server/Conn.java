package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import server.Server;

/**
 * Created by Bror on 18.11.2016.
 */

public class Conn extends Thread {

    private boolean godtatt = false;
    private DataOutputStream dos;
    private DataInputStream dis;

    private Socket socket;

    public Conn(Socket isocket) {
        socket = isocket;
    }

    /* Bug test av forbindelse */
    public void run() {
        try {
            PrintStream output = new PrintStream(socket.getOutputStream());
            output.println("itzzz workzzz");
        } catch (Exception e) {
            System.err.print("Melding ikke motatt");
        }
    }

}