package gui;

import java.awt.*;
import javax.swing.*;

/**
 * Created by Weeto on 01/12/2016.
 */
public class SpillBrett extends JPanel {

        public SpillBrett() {
            //setBackground(Color.BLUE);
            setLayout(new GridLayout(8,0 /*, 5, 5 */ ));
            for (int i = 0; i<64; i++) {
                add(new RuteMatrise(i));
            }
        }

    }