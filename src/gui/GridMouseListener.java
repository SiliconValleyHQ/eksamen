package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by mariuswetterlin on 14.12.2016.
 */
public class GridMouseListener implements MouseListener {
	@Override
	public void mouseClicked(MouseEvent e) {
		Grid grid = (Grid) e.getSource();
		Rectangle r = grid.getRectangle(e.getX(), e.getY());
		r.toggleActive();
		grid.clicked(r);

		grid.repaint();
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
