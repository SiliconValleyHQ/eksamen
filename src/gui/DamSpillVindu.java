package gui;

import javax.swing.*;
import java.awt.*;
// import spillLogikk.SpillData;

/**
 * Created by Bror on 30.11.2016.
 */
public class DamSpillVindu extends Canvas {

    // private SpillData spillbrett;

    public DamSpillVindu() {
        JFrame vindu = new JFrame();
        vindu.setBackground(Color.black);
        vindu.setFont(new Font("verdana", Font.BOLD, 16));
        vindu.setSize(400,400);
        vindu.setVisible(true);
    }

}
