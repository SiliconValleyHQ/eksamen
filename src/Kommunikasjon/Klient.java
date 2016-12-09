package Kommunikasjon;

import gui.DamVindu;

import java.io.*;
import java.net.Socket;

/**
 * Created by Bror on 08.12.2016.
 */
public class Klient implements Runnable {

    int port = 22222;
    String ip = "127.0.0.1";
    Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private Thread thread;

    public Klient() throws IOException {
        forbindelse();
        //new DamVindu();
    }

    public boolean forbindelse() {
        while (true) {
            try {
                socket = new Socket(ip, port);
                dos = new DataOutputStream(socket.getOutputStream());
                dis = new DataInputStream(socket.getInputStream());
                System.out.println("Forbindelse?");
                thread = new Thread();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }

    @Override
    public void run() {
        getServerGame();
    }

    public DataInputStream getDataInputStream() {
        return dis;
    }

    public DataOutputStream getDataOutputStream() {
        return dos;
    }

}
