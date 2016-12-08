package gui;

/**
 * Created by Weeto on 01/12/2016.
 */
    import java.awt.Color;
    import java.awt.event.MouseEvent;
    import java.awt.event.MouseListener;

    import javax.swing.JPanel;

    public class RuteMatrise extends JPanel implements MouseListener {

        private int position;
        final String[] kolonnebokstaver = { "A", "B", "C", "D", "E", "F", "G", "H"};

        public RuteMatrise(int position) {
            this.position = position;
            setBackground(calculateColor(position));
            addMouseListener(this);
        }
        private Color calculateColor(int p) {
            boolean annenHverRute = (p % 2 == 0);
            boolean annenHverRad = (p / 8) % 2 ==0;
            return annenHverRute != annenHverRad ? Color.black : Color.white;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            String bokstav = kolonnebokstaver[position % 8];
            System.out.println(position + " " + bokstav + ((position / 8)+1));
        }
        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub

        }
        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }
        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }
        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }

    }

