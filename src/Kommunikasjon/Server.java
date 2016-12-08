package Kommunikasjon;


import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import java.net.ServerSocket;

/**
 * Created by Bror on 18.11.2016.
 */

public class Server extends Thread {

    public static final int SERVER_TICK = 10;
    private ServerSocket serverSocket;
    private Konfigurering cfg;
    private ServerGame game;

    public Server(int portNummer) {
        this.cfg = new Konfigurering(portNummer);
    }

    public static int getServerTick {

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

}