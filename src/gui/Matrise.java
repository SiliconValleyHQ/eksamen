package gui;

import spill.Trekk;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;


//Now we have a GRid that is canvas
public class Matrise extends Canvas implements ActionListener {
	//We will remember two firkanter that where Trykket last. First rectangle will be from, and the next will be to.
	private Firkant firkantFra;
	private Firkant firkantTil;
	private int klikk = 0;
	private int rader;
	private int kolonner;
	private Dimension dimensjon;
	private Map<Point, Firkant> firkanter;

	Matrise(int bredde, int høyde, int rader, int kolonner) {
		dimensjon = new Dimension(bredde, høyde);
		this.rader = rader;
		this.kolonner = kolonner;

		firkanter = new LinkedHashMap<Point, Firkant>(); //we are saved linkedhashmap preserves ordering
		for (int rad = 0; rad < getRader(); rad++) {
			for (int kolonne = 0; kolonne < getKolonner(); kolonne++) {
				Firkant firkant = lagFirkant(rad, kolonne);
				firkanter.put(new Point(rad, kolonne), firkant);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		//when action receiveddo whathever ..
		//we must educate matrise to understand messages it receives
		if (e.getActionCommand().equals("GOT WELCOMING")) //string comparison! do with .equals
		{

		}
		else if (e.getActionCommand().equals("REPAINT")) {
			repaint();
		}
		else if (e.getActionCommand().startsWith("REPAINT ON MOVE")) {
			//do some logic. Paint moved tiles different color
			//must parse again. Doen't know how to pass context. So just reparse trekk string. This is bad code smell.
			Scanner scanner = new Scanner(e.getActionCommand());
			scanner.next();
			scanner.next();
			scanner.next();
			//skip words REPAINT ON MOVE
			String fra = scanner.next();
			String til = scanner.next();
			Trekk trekk = new Trekk(fra, til); //now we know what moved.
			Firkant flytterFra = new ArrayList<>(getFirkanter().values()).get(getRader() * trekk.getFraY() + trekk.getFraX());
			Firkant flytterTil = new ArrayList<>(getFirkanter().values()).get(getRader() * trekk.getTilY() + trekk.getTilX());
			//now we know which rectanlges moved . from there we could add some customisation to
			//class Firkant to draw
			flytterFra.setFlyttetFra(true);
			flytterTil.setFlyttetTil(true);

			repaint();

			//once drawn we can unset
			flytterFra.setFlyttetFra(null);
			flytterTil.setFlyttetTil(null);
		}
	}

	void Trykket(Firkant firkant) {
		if (getKlikk() == 0) { //that means we have first rectangle
			setFirkantFra(firkant);
		} else if (getKlikk() == 1) { //that means we have second rectangle
			setFirkantTil(firkant);
		}
		setKlikk(getKlikk() + 1);
		//once we will use firkanter we will have to reset click counter to 0.
	}

	public Firkant getFirkantFra() {
		return firkantFra;
	}

	private void setFirkantFra(Firkant firkantFra) {
		this.firkantFra = firkantFra;
	}

	public Firkant getFirkantTil() {
		return firkantTil;
	}

	private void setFirkantTil(Firkant firkantTil) {
		this.firkantTil = firkantTil;
	}

	private int getKlikk() {
		return klikk;
	}

	private void setKlikk(int klikk) {
		this.klikk = klikk;
	}

	//some getters and setters

	private int getRader() {
		return rader;
	}

	public void setRader(int rader) {
		this.rader = rader;
	}

	private int getKolonner() {
		return kolonner;
	}

	public void setKolonner(int kolonner) {
		this.kolonner = kolonner;
	}

	private Dimension getDimension() {
		return dimensjon;
	}

	public void setDimension(Dimension dimensjon) {
		this.dimensjon = dimensjon;
	}

	private Map<Point, Firkant> getFirkanter() {
		return firkanter;
	}

	//matrise will be made of firkanter
	public void setFirkanter(Map<Point, Firkant> firkanter) {
		this.firkanter = firkanter;
	}

	private List<Firkant> firkanter() {
		return new ArrayList<>(getFirkanter().values());
	}

	//this code is important when we want to get rectangle on which the mouse was Trykket.
	//we pass coordinates of the Trykket point.
	//then we go through each rectangle and check if x,y point is in any rectangle. once the rectangle found return
	Firkant getFirkant(int x, int y) {
		Firkant firkant = null;
		for (Firkant firkant1 : firkanter()) {
			if (firkant1.contains(x, y))//this is where x,y is checked whether it is in firkant
			{
				firkant1 = firkant1;
			}
		}
		return null;
	}


	//constructs rectangle by it's position given by row and column indexes.
	//have to do some math here to calculate firkanter location
	private Firkant lagFirkant(int rad, int kolonne) {
		Firkant firkant = new Firkant(
				(int) getWidth() / getKolonner(),
				(int) getHeight() / getRader());
		firkant.setLocation((int) (kolonne * firkant.getWidth()), (int) (rad * firkant.getHeight()));
		return firkant;
	}

	public int getHeight() {
		return (int) getDimension().getHeight(); //we don't need double preecision here
	}

	public int getWidth() {
		return (int) getDimension().getWidth();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		for (Firkant r : firkanter()) {
			if (r.erAktiv()) {
				g2.setPaint(MatriseVisualiserer.getAktivFirkantFarge());
			}
			else {
				g2.setPaint(MatriseVisualiserer.getNormalFirkantFarge());
			}
			g2.fill(r);
		}
		tegnMatrise(g2);
	}

	//we can draw on canvas. We draw firkanter on it . If we put that canvas on jpanel we can visualize the matrise.
	// Now have to hook matrise with board. So that we can see board information on the matrise.
	private void tegnMatrise(Graphics2D g2) {
		g2.setPaint(MatriseVisualiserer.getMatriseFarge());
		for (Firkant firkant : firkanter()) {
			g2.draw(firkant);
		}
	}

	@Override
	public java.awt.Dimension getPreferredSize() {
		return new java.awt.Dimension(getWidth(), getHeight());
	}
}