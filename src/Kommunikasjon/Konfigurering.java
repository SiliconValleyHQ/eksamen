package Kommunikasjon;

/**
 * Created by Bror on 08.12.2016.
 */
public class Konfigurering {

    private String hostNavn;
    private int portNummer;

    public Konfigurering(int portNummer) {
        this.portNummer = portNummer;
    }

    public Konfigurering(String hostNavn, int portNummer) {
        this.hostNavn = hostNavn;
        this.portNummer = portNummer;
    }

    public void setHostNavn (String hostNavn) {
        this.hostNavn = hostNavn;
    }

    public  void setPortNummer (int portNummer) {
        this.portNummer = portNummer;
    }

}
