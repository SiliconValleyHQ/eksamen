package gui;

import java.awt.*;

public class MatriseVisualiserer {

	/**
	 * Her skjer visualiseringen av matrisen. Her setter fargen på matrisen, og hvilke farge enkelte Firkanter har
	 * i forskjellig tilstand.
	 * @return
	 */
	static Color getAktivFirkantFarge() {
		return Color.GREEN;
	}

	static Color getNormalFirkantFarge() {
		return Color.CYAN;
	}

	static Color getMatriseFarge() {
		return Color.BLACK;
	}

	public static Color getFlyttetFraFarge() {
		return Color.BLUE;
	}

	public static Color getFlyttetTilFarge() {
		return Color.YELLOW;
	}

}