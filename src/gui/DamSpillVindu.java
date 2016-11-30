package gui;

import javax.swing.*;
import java.awt.*;
import spillLogikk.*;


/**
 * Created by Bror on 30.11.2016.
 */
public class DamSpillVindu extends Canvas {

    private SpillData spillbrett;

    public DamSpillVindu() {

        spillbrett = new SpillData();
        SpillData nyttspill = new SpillData();
        nyttspill.startNyttSpill("Start nytt spill");

    }

}