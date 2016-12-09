package Kommunikasjon;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Bror on 18.11.2016.
 */

public class Server implements Runnable {

    private Scanner scanner = new Scanner(System.in);
    private Thread thread;
    private Socket socket;
    private ServerSocket serverSocket;
    private DataInputStream dis;
    private DataOutputStream dos;

    int port = 22222;
    String ip = "127.0.0.1";

    boolean forbindelse = new Klient().forbindelse();


    public Server() throws IOException {

        System.out.println("skriv inn porten du ønsker serveren skal være på");
        port = scanner.nextInt(); //Henter inn innskrevet portnr

        ServerSocket serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
        System.out.println("Opprettet server på port " + port);
        
        if (!forbindelse) {
            nyServerSocket();
        }

        thread = new Thread( this, "Server");
        thread.start();

    }

    private void nyServerSocket() {
        while (true) {
            try {
                serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
                System.out.println("hallo?");
            } catch (Exception e) {
                continue;
            }
        }
    }

    public void run() {
        while (true) {
            //lyttEtterKlient();
        }
    }

    /*private void lyttEtterKlient() {
        Klient klientSocket = null;
        try {
            klientSocket = new serverSocket.accept();
            dos = new DataOutputStream(klientSocket.getDataOutputStream());
            dis = new DataInputStream(klientSocket.getDataInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}