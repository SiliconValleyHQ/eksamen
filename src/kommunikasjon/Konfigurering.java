package kommunikasjon;

/**
 * Created by Bror on 08.12.2016.
 */
public class Konfigurering
{
    private String hostNavn;
    private int portNummer;

    public Konfigurering(int portNummer)
    {
        this.portNummer = portNummer;
    }

    public Konfigurering(String hostNavn, int portNummer)
    {
        this.portNummer = portNummer;
        this.hostNavn = hostNavn;
    }

    public void setHostNavn(String hostNavn)
    {
        this.hostNavn = hostNavn;
    }

    public String getHostNavn()
    {
        return hostNavn;
    }

    public void setPortNummer(int portNummer)
    {
        this.portNummer = portNummer;
    }

    public int getPortNummer()
    {
        return portNummer;
    }
}
