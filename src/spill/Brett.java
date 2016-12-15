package spill;

import java.util.Arrays;

public class Brett {

	private int bredde; //width
	private int høyde; //høyde
	private Rute[] brett; //Rute[] board;

	private Brett(int xDimensjon, int yDimensjon) {
		this.bredde = xDimensjon;
		this.høyde = yDimensjon;
		initBrett();
	}

	Brett() {
		this(8, 8);
	}

	static Rute[] spillbareRuter() {
		return new Rute[]{
				Rute.RED, Rute.BLACK
		};
	}

	private void initBrett() {
		brett = new Rute[getBredde() * getHøyde()];
		Arrays.fill(brett, Rute.EMPTY);
	}

	private Rute[] getBrett() {
		return brett;
	}

	public void setBrett(Rute[] brett) {
		this.brett = brett;
	}

	private int getBredde() {
		return bredde;
	}

	void setBredde(int bredde) {
		this.bredde = bredde;
	}

	private int getHøyde() {
		return høyde;
	}

	public void setHøyde(int høyde) {
		this.høyde = høyde;
	}

	private Rute getRute(int x, int y) {
		return getBrett()[getBredde() * x + y];
	}

	void setRute(int x, int y, Rute rute) {
		brett[getBredde() * x + y] = rute;
	}

	boolean erOkkupert(int x, int y) {
		return !erTom(x, y);
	}

	private boolean erTom(int x, int y) {
		return getRute(x, y).equals(Rute.EMPTY);
	}

	public enum Rute {
		RED, BLACK, EMPTY
	}

}