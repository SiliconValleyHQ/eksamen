package server;

import gui.DamSpillVindu;
import gui.GraphicUserInterface;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Created by Bror on 18.11.2016.
 */

public class Server implements Runnable {

    /*
    * Variabler som serveren trenger, satt til noen standardverdier.
    * Sortert etter hva slags type variabel det er.
     */
    public String ip = "127.0.0.1";

    public int port = 22222;

    private Scanner scanner = new Scanner(System.in);
    private Thread thread;
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private ServerSocket serverSocket;

    private boolean godtatt = false;

    /*
    * Server() har hovedansvaret for det som skjer med serveren.
     */
    public Server() {
        System.out.println("Skriv inn ip-adressen");
        ip = scanner.nextLine(); //Henter inn info som er skrevet i konsoll.
        System.out.println("skriv inn en port");
        port = scanner.nextInt(); //Henter inn innskrevet portnr
        while (port < 1 || port > 66666) { //Sjekker om innskrevet portnr er ett gyldig portnr, og har en verdi mellom 1 og 66666
            System.out.println("Porten du skrev inn er ikke en gyldig port mellom 1 og 66666");
            port = scanner.nextInt();
        }
        if (!connect()) { //Hvis ikke connect() er mulig vil programmet starte oppstartAvNyServer()
            oppstartAvNyServer();
        }

        thread = new Thread(this, "Server");
        thread.start(); //Starter en ny tråd

    }

    /*
    * Hvis tilkoblingen ikke er mulig (ikke godtatt) vil programmet starte lytterEtterAnnenServer()
     */
    public void run() {
        while (true) {
            if (!godtatt) {
                lytterEtterAnnenServer();
            }
        }
    }

    /*
    * Denne metoden starter en ny ServerSocket på oppgitt port og IP-adresse
     */
    private void oppstartAvNyServer() {
        try {
            serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
            System.out.println("Opprettet server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    * Denne metoden har som oppgave å se om det er noen som prøver å koble til den.
    * Da vil godtatt = true, og en oppkobling skjer når socket = serverSocket.accepted();
     */
    private void lytterEtterAnnenServer() {
        Socket socket = null;
        try {
            socket = serverSocket.accept();
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            godtatt = true;
            System.out.println("Koblet til spiller");
            new GraphicUserInterface(); //åpner Vinduet som spillet skal kjøres i
            System.out.println("Åpnet GUI for spill");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    * Connect sender en forespørsel og ser om det er noe å koble til på oppgitt IP og Port.
     */
    private boolean connect() {
        try {
            socket = new Socket(ip, port);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            godtatt = true;
            System.out.println("Har opprettet forbindelse med serveren");
            new GraphicUserInterface(); //åpner Vinduet som spillet skal kjøres i
<<<<<<< HEAD
            System.out.println("Åpnet GUI for spill");
=======
            System.out.println("Åpne GUI for spill");
>>>>>>> origin/Marius
        } catch (IOException e) {
            System.out.println("Kunne ikke koble til: " + ip + " " + port + " || Oppretter en ny server");
            return false;
        }
        return true;
    }

}