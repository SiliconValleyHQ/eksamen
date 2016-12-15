package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MatriseMuselytter implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		Matrise matrise = (Matrise) mouseEvent.getSource();
		Firkant firkant = matrise.getFirkant(mouseEvent.getX(), mouseEvent.getY());
		firkant.toggleActive();
		matrise.Trykket(firkant);

		matrise.repaint();
	}

	@Override
	public void mousePressed(MouseEvent mouseEvent) {

	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent) {

	}

	@Override
	public void mouseEntered(MouseEvent mouseEvent) {

	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {

	}
}
