package spillLogikk;

import server.Server;

/**
 * Created by Bror on 30.11.2016.
 */
public class SpillRegler {

    //TODO Få hentet variablen spiller fra server klassen.
    Server sattSpiller = new Server();
    int spiller = sattSpiller.hentSpiller();

    public SpillRegler() {
        definerSpiller();
    }

    public int ROD = 1;
    public int SVART = 2;

    public void definerSpiller() {
        if (spiller == 1) {
            spiller = ROD;
            System.out.println("Spiller er rød");
        } else if (spiller == 2) {
            spiller = SVART;
            System.out.println("Spiller er svart");
        } else {
            System.out.println("Noe gikk galt, under definering av spillere.");
        }
    }

}
