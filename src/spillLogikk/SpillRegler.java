package spillLogikk;

import static com.sun.org.apache.xpath.internal.compiler.OpCodes.EMPTY;
import static java.awt.Color.*;

/**
 * Created by Bror on 30.11.2016.
 */
public class SpillRegler {

    public void settOppNyttSpill() {

        int[][] spillBrett = new SpillData().spillBrett;

        /*
        * Denne for loopen skal sørge for at halvparten av brikkene
        * skal være svarte og andre halvparten røde.
        * De første to radene (rad < 3) skal brikkene være svarte, og
        * radene rad > 4 skal brikkene være røde.
        */
        for (int rad = 0; rad < 8; rad++) {
            for (int kolonne = 0; kolonne < 8; kolonne++) {
                if (rad % 2 == kolonne % 2) {
                    if (rad < 3) {
                        //spillBrett[rad][kolonne] = BLACK;
                    } else if (rad > 4) {
                        //spillBrett[rad][kolonne] = RED;
                    } else {
                        spillBrett[rad][kolonne] = EMPTY;
                    }
                } else {
                    spillBrett[rad][kolonne] = EMPTY;
                }
            }
        }
    } //Oppsettet av spillet er ferdig

}