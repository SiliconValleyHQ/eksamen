package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import spillLogikk.*;

/**
 * Created by Bror on 30.11.2016.
 */
public class DamSpillVindu extends Canvas implements ActionListener, MouseListener {

    Button giOppKnapp;

    SpillData spillbrett;

    public DamSpillVindu() {
        JFrame vindu = new JFrame();
        vindu.setBackground(Color.black);
        vindu.addMouseListener(this);
        vindu.setFont(new Font("verdana", Font.BOLD, 16));
        vindu.setSize(400,400);
        vindu.setVisible(true);

        giOppKnapp = new Button("Gi opp");
        giOppKnapp.addActionListener(this);

        spillbrett = new SpillData();

        SpillData nyttspill = new SpillData();
        nyttspill.startNyttSpill("Start nytt spill");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
