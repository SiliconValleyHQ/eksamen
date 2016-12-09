package kommunikasjon;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

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
    private Scanner scanner = new Scanner(System.in);

    public Klient() throws IOException {
        forbindelse();
        //new DamVindu();
    }

    public static void main (String[] args) {
        try {
            new Klient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean forbindelse() {
        while (true) {
            try {
                System.out.println("Skriv inn ip-adressen til serveren");
                ip = scanner.nextLine(); //Henter inn info som er skrevet i konsoll.
                System.out.println("skriv inn en porten til serveren");
                port = scanner.nextInt(); //Henter inn innskrevet portnr
                socket = new Socket(ip, port);
                dos = new DataOutputStream(socket.getOutputStream());
                dis = new DataInputStream(socket.getInputStream());
                thread = new Thread();
                System.out.println("greaaaaat success?");
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }

    @Override
    public void run() {
        while (true) {
            //hva skal stå her?;
        }
    }

    public DataInputStream getDataInputStream() {
        return dis;
    }

    public DataOutputStream getDataOutputStream() {
        return dos;
    }


}
