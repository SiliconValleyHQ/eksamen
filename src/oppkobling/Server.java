package oppkobling;

import gui.DamVindu;
import spillLogikk.Spiller;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Created by Bror on 18.11.2016.
 */

public class Server {

    /*
    * Variabler som serveren trenger, satt til noen standardverdier.
    * Sortert etter hva slags type variabel det er.
     */
    public String ip = "127.0.0.1";

    public int port = 22222;

    private Scanner scanner = new Scanner(System.in);
    private Thread thread;
    private Socket s;
    private DataOutputStream dos;
    private DataInputStream dis;
    private ServerSocket serverSocket = new ServerSocket(port);
    public int spiller;

    private boolean godtatt = false;

    /*
    * Enkel oppkobling som parer opp med Klient som kobler seg til
     */
    public Server() throws Exception {
        ServerSocket serverSocket = this.serverSocket;
        System.out.println("Starter oppkobling");
        try {
            while (true) {
                new DamVindu();
                Spiller spiller1 = new Spiller(serverSocket.accept(), '1');
                Spiller spiller2 = new Spiller(serverSocket.accept(), '1');
                spiller1.setMotstander(spiller2);
                spiller2.setMotstander(spiller1);
                Spiller.ns = spiller1;
                spiller1.start();
                spiller2.start();
            }
        } finally {
            serverSocket.close();
        }

    }


    /*
    * Server() har hovedansvaret for det som skjer med serveren.

    public Server() throws Exception {
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

        try {
            while (true) {
                new DamVindu();
                System.out.println("Åpne GUI for spill");
                Spiller spiller1 = new Spiller(serverSocket.accept(), '1');
                System.out.println("kom spiller 2 hit?");
                Spiller spiller2 = new Spiller(serverSocket.accept(), '2');
                spiller1.setMotstander(spiller2);
                spiller2.setMotstander(spiller1);
                //Spiller ns = spiller1;
                spiller1.start();
                spiller2.start();

                thread = new Thread(this, "Server");
                thread.start(); //Starter en ny tråd
            }
        } finally {

        }

    }
    */

    /*
    * Hvis tilkoblingen ikke er mulig (ikke godtatt) vil programmet starte lytterEtterAnnenServer()
     */
    /*public void run() {
        while (true) {
            if (!godtatt) {
                lytterEtterAnnenServer();
            }
        }
    }
    */

    /*
    * Denne metoden starter en ny ServerSocket på oppgitt port og IP-adresse
     */
    /*private void oppstartAvNyServer() {
        try {
            serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
            System.out.println("Opprettet oppkobling");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /*
    * Denne metoden har som oppgave å se om det er noen som prøver å koble til den.
    * Da vil godtatt = true, og en oppkobling skjer når s = serverSocket.accepted();
     */
    /*public void lytterEtterAnnenServer() {
        Socket socket = null;
        try {
            socket = serverSocket.accept();
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            godtatt = true;
            System.out.println("Koblet til spiller");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /*
    * Connect sender en forespørsel og ser om det er noe å koble til på oppgitt IP og Port.
     */
    /*public boolean connect() {
        try {
            s = new Socket(ip, port);
            dos = new DataOutputStream(s.getOutputStream());
            dis = new DataInputStream(s.getInputStream());
            godtatt = true;
            System.out.println("Har opprettet forbindelse med serveren");
        } catch (IOException e) {
            System.out.println("Kunne ikke koble til: " + ip + " " + port + " || Oppretter en ny oppkobling");
            return false;
        }
        return true;
    }*/

}