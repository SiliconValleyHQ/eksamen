package kommunikasjon;

//import gui.DamVindu;

import gui.SpillBrett;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Bror on 08.12.2016.
 */
public class Klient {

    int port = 22222;
    String ip = "127.0.0.1";
    Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    public Thread thread;
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
            System.out.println("boooom");
            try {
                System.out.println("Skriv inn ip-adressen til serveren");
                ip = scanner.nextLine(); //Henter inn info som er skrevet i konsoll.
                System.out.println("skriv inn en porten til serveren");
                port = scanner.nextInt(); //Henter inn innskrevet portnr
                socket = new Socket(ip, port);
                dos = new DataOutputStream(socket.getOutputStream());



                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                SpillBrett minMottatMail = (SpillBrett) objectInputStream.readObject();
                minMottatMail.hvemErJeg();



                dis = new DataInputStream(socket.getInputStream());
                thread = new Thread();
                System.out.println("greaaaaat success?");
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        return true;

    }

    public DataInputStream getDataInputStream() {
        return dis;
    }

    public DataOutputStream getDataOutputStream() {

        return dos;
    }


}
