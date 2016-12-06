package gui;

import java.awt.*;
import spillLogikk.*;

/**
 * Created by Bror on 30.11.2016.
 */
public class DamSpill extends Canvas {

    SpillData spillbrett;

    public DamSpill() {

        spillbrett = new SpillData();
        SpillData nyttspill = new SpillData();
        nyttspill.startNyttSpill();

    }

}
