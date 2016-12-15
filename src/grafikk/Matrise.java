package grafikk;

import spill.Trekk;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Dette er matrisen. Den utvider Canvas og henter informasjon fra
 * en ActionListner som bestemmer hva som skal skje når det blir gjort noe.
 * @var firkantFra
 * 		Første Firkant vil være der trekket skal bli gjort fra
 * @var firkantTil
 * 		Andre Firkant vil være der brikken skal lande.
 */
public class Matrise extends Canvas implements ActionListener {
	//her ønsker vi å lagre hvor brukeren sist trykket.
	private Firkant firkantFra;
	private Firkant firkantTil;
	private int klikk = 0;
	private int rader;
	private int kolonner;
	private Dimension dimensjon;
	private Map<Point, Firkant> firkanter;

	/**
	 * Her tegner vi en matrise som henter argumenter fra grafikk.Klient. For å sette størrelsen på matrisen bruker i awt metoden Dimension.
	 * Vi henter også ut og lagrer hvor mange rader og kolonner det skal være.
	 */
	Matrise(int bredde, int høyde, int rader, int kolonner) {
		dimensjon = new Dimension(bredde, høyde);
		this.rader = rader;
		this.kolonner = kolonner;

		firkanter = new LinkedHashMap<Point, Firkant>(); //Dette reddet oss litt, da vi fant ut at linkhashmap lagrer sortering
		for (int rad = 0; rad < getRader(); rad++) {
			for (int kolonne = 0; kolonne < getKolonner(); kolonne++) {
				Firkant firkant = lagFirkant(rad, kolonne);
				firkanter.put(new Point(rad, kolonne), firkant);
			}
		}
	}

	/**
	 * Her definerer vi hva som skal bli gjort, bassert på hva brukeren trykket på.
	 * Når programmet har motatt en action skal det utføre riktig handlig. Her må vi lære opp matrisen til hva den skal gjøre.
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
		//Dette er Stringen vi sammenligner, det blir gjort med .equals
		if (e.getActionCommand().equals("MOTATT VELKOMSTMELDING")) {

		} else if (e.getActionCommand().equals("REPAINT")) {
			repaint();
		} else if (e.getActionCommand().startsWith("REPAINT ETTER TREKK")) {
			// Her ønsker vi det skal være noen logikk som faktisk repainter når ett trekk blir gjort.
			// Her fant vi ingen god utvei, og må ha dupicatkode fra nettverk.Klient
			Scanner scanner = new Scanner(e.getActionCommand());
			scanner.next();
			String fra = scanner.next();
			String til = scanner.next();
			Trekk trekk = new Trekk(fra, til);
			Firkant flytterFra = new ArrayList<>(getFirkanter().values()).get(getRader() * trekk.getFraY() + trekk.getFraX());
			Firkant flytterTil = new ArrayList<>(getFirkanter().values()).get(getRader() * trekk.getTilY() + trekk.getTilX());
			//her vet vi hvilke Firkanter det er skjedd noe i, og vi trenger Firkant til å kunne repainte seg med ny riktig informasjon
			flytterFra.setFlyttetFra(true);
			flytterTil.setFlyttetTil(true);

			repaint();

			//Når trekket er gjort nullstilles flytterFra og flytterTil
			flytterFra.setFlyttetFra(null);
			flytterTil.setFlyttetTil(null);
		}
	}

	void Trykket(Firkant firkant) {
		if (getKlikk() == 0) { //dette betyr vi har den første Firkanten
			setFirkantFra(firkant);
		} else if (getKlikk() == 1) { //dette betyr vi har den andre Firkanten
			setFirkantTil(firkant);
		}
		setKlikk(getKlikk() + 1);
		// Når vi har firkantene og skal bruke de. Vil vi at countern skal til 0.
	}


	/**
	 * Gettere og Settere som tillater oss å sende informasjon mellom de forskjellige klassene.
	 */
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

	//her bestemmer vi at Matrisen skal bestå av Firkant
	public void setFirkanter(Map<Point, Firkant> firkanter) {
		this.firkanter = firkanter;
	}

	private List<Firkant> firkanter() {
		return new ArrayList<>(getFirkanter().values());
	}


	/**
	 * Denne koden er viktig. Det er her vi sjekker om en Firkant er blitt trykket på.
	 * Koden går da gjennom og sjekker koordinatene til Firkanten før den returnerer firkanten.
	 */
	Firkant getFirkant(int x, int y) {
		Firkant firkant = null;
		for (Firkant firkant1 : firkanter()) {
			//Det er er vi sjekker x og y, og ser om det er inne i en firkant
			if (firkant1.contains(x, y)) {
				firkant1 = firkant1;
			}
		}
		return null;
	}

	/**
	 * Her lager vi en firkant bassert på hvilke posisjon rad og kolonner tilsier.
	 */
	private Firkant lagFirkant(int rad, int kolonne) {
		Firkant firkant = new Firkant((int) getWidth() / getKolonner(), (int) getHeight() / getRader());
		firkant.setLocation((int) (kolonne * firkant.getWidth()), (int) (rad * firkant.getHeight()));
		return firkant;
	}

	public int getHeight() {
		return (int) getDimension().getHeight();
	}

	public int getWidth() {
		return (int) getDimension().getWidth();
	}

	/**
	 * Her skifter vi farge på firkanten, hvis den er trykket på er den aktiv, hvis den blir trykket på igjen
	 * er den ikke aktiv. Her henter vi informasjon fra klassen MatriseVisualiserer
	 */
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		for (Firkant firkant : firkanter()) {
			if (firkant.erAktiv()) {
				g2.setPaint(MatriseVisualiserer.getAktivFirkantFarge());
			} else {
				g2.setPaint(MatriseVisualiserer.getNormalFirkantFarge());
			}
			g2.fill(firkant);
		}
		tegnMatrise(g2);
	}

	/**
	 * Vi kan her tegne på Canvasen. Vi tegner nå firkanter på det. Hvis vi putter Canvasen på en JPanel, kan vi
	 * visualisere matrisen.
	 */
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