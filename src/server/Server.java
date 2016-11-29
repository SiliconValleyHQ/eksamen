package server;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Created by Bror on 18.11.2016.
 */

public class Server implements Runnable {

    public String ip = "localhost";
    public int port = 28702;
    private Scanner scanner = new Scanner(System.in);
    private Thread thread;

    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;

    private ServerSocket serverSocket;

    private boolean godtatt = false;

    public Server() {
        System.out.println("Skriv inn ip-adressen");
        ip = scanner.nextLine();
        System.out.println("skriv inn en port");
        port = scanner.nextInt();
        while (port < 1 || port > 66666) {
            System.out.println("Porten du skrev inn er ikke en gyldig port mellom 1 og 66666");
            port = scanner.nextInt();
        }
        if (!connect()) {
            oppstartAvNyServer();
        }

        thread = new Thread(this, "TestServer");
        thread.start();

    }

    public void run() {
        while (true) {
            if (!godtatt) {
                lytterEtterAnnenServer();
            }
        }
    }

    private void oppstartAvNyServer() {
        try {
            serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
            System.out.println("Opprettet server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void lytterEtterAnnenServer() {
        Socket socket = null;
        try {
            socket = serverSocket.accept();
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            godtatt = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean connect() {
        try {
            socket = new Socket(ip, port);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            godtatt = true;
        } catch (IOException e) {
            System.out.println("Kunne ikke koble til: " + ip + " " + port + " || Oppretter en ny server");
            return false;
        }
        System.out.println("Har opprettet forbindelse med serveren");
        return true;
    }

}