package gui;

import java.awt.*;
import spillLogikk.*;

/**
 * Created by Bror on 30.11.2016.
 */
public class DamSpillVindu extends Canvas {

    SpillData spillbrett;

    public DamSpillVindu() {

        spillbrett = new SpillData();
        SpillData nyttspill = new SpillData();
        nyttspill.startNyttSpill();

    }

}
