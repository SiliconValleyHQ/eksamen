package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by mariuswetterlin on 30.11.2016.
 */

public class DamVindu extends Canvas implements ActionListener, MouseListener  {

    private Button giOppKnapp;

    public DamVindu() {

        usualStuff();
        new DamSpill();

        giOppKnapp = new Button("Gi opp");
        giOppKnapp.addActionListener(this);
    }

    private void usualStuff() {
        JFrame vindu = new JFrame();
        vindu.add(new SpillBrett());
        vindu.setBackground(Color.RED);
        vindu.addMouseListener(this);
        vindu.setFont(new Font("verdana", Font.BOLD, 16));
        vindu.setSize(600,450);
        vindu.setVisible(true);
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
