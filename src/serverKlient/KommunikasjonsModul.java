package serverKlient;

import spill.Brett;
import spill.Brikke;
import spill.DamSpill;
import spill.Rute;
import sun.plugin2.message.Serializer;

import java.io.*;
import java.net.*;


import static spill.Brett.move;
import static spill.Brett.rader;

/**
 * Created by Bror on 08.12.2016.
 * Klasse for å holde på spill informasjon
 */
public class KommunikasjonsModul implements Runnable {

	private Socket socket;
	private PrintWriter output;
	private BufferedReader input;
	private boolean spiller1tur = false;
	private boolean spiller2tur = false;

	protected KommunikasjonsModul(Socket klientSocket) throws IOException {
		spiller1tur = true; //Sørger for at spiller 1 får første trekk
		this.socket = klientSocket;

		//Dette skal i teori kunne sende spillet fra en klient til en annen.
		DamSpill spillBrett = new DamSpill();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(klientSocket.getOutputStream());
		objectOutputStream.writeObject(spillBrett);

		objectOutputStream.close();
	}

	private void utfortTrekk() throws Exception {
		spiller1tur = !spiller1tur;
		spiller2tur = !spiller2tur;

		sendBrett();
	}

    private Serializer getData(byte[] b) {
        Serializer serializer = null;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(b);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            serializer = (Serializer) objectInputStream.readObject();
            System.out.println("Mottatt en pakke");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serializer;
    }

    private void taImotBrett(Socket klientSocket) {
        Brett brett = null;

        try {
            FileInputStream fileInputStream = new FileInputStream("/tmp/logg.ser");
            ObjectInputStream inn = new ObjectInputStream(fileInputStream);
            brett = (Brett) inn.readObject();
            inn.close();
            fileInputStream.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Brett ikke funnet");
            c.printStackTrace();
            return;
        }

    }

    private void sendBrett() throws Exception {
        Brett brett = new Brett();
        Rute rute = new Rute(this, this, this);
        Brikke brikke = new Brikke(this, this, this);
        brett.move(rute, rute);
        brett.getRute(rute.getRad(), rute.getKolonne());
        brett.hentMuligeTrekk(brikke);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/tmp/logg.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(brett);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

	private boolean isSpiller2tur() {
		return spiller2tur;
	}

	@Override
	public void run() {

	}

}