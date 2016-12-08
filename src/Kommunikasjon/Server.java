package Kommunikasjon;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by Bror on 18.11.2016.
 */

public class Server extends Thread {

    public static final int SERVER_TICK = 10;
    private ServerSocket serverSocket;
    private Konfigurering cfg;
    //private ServerGame game;

    public Server(int portNummer) {
        this.cfg = new Konfigurering(portNummer);
    }

    public int getPortNummer() {
        return cfg.getPortNummer();
    }

    public void lagServer() {
        try {
            setServerSocket(new ServerSocket(getPortNummer()));
            System.out.println("startet server på " + getPortNummer());
        } catch (IOException e) {
            System.err.println("Kunne ikke lytte på port " + getPortNummer());
        }
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void sleep() {
        try {
            Thread.sleep(getServerTick());
        } catch (InterruptedException e) {
            System.err.println("Server thread interruption!" + e);
        }
    }

    public static int getServerTick() {
        return SERVER_TICK;
    }

    public Object getKonfigurering() {
        return cfg;
    }
}