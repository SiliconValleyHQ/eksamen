package gui;

public class Firkant extends java.awt.Rectangle {

	private Boolean flyttetFra = null;
	private Boolean flyttetTil = null;
	private boolean aktiv;

	Firkant(int x, int y) {
		super(x, y);
	}

	public Boolean getFlyttetFra() {
		return flyttetFra;
	}

	void setFlyttetFra(Boolean flyttetFra) {
		this.flyttetFra = flyttetFra;
	}

	public Boolean getFlyttetTil() {
		return flyttetTil;
	}

	void setFlyttetTil(Boolean flyttetTil) {
		this.flyttetTil = flyttetTil;
	}

	private boolean getAktiv() {
		return aktiv;
	}

	boolean erAktiv() {
		return aktiv;
	}

	private void setAktiv(boolean aktiv) {
		this.aktiv = aktiv;
	}

	void toggleActive() {
		setAktiv(!getAktiv());
	}
}