package spill;

import java.util.*;

/**
 * Denne klassen forteller klientene om statusen til spillet.
 * Det er her mye logikk kalkuleres.
 */
public class Spill {

	private Map<Object, Brett.Rute> plass = new HashMap<>();

	private Brett.Rute vinnerRute() {
		return Brett.Rute.EMPTY;
	}

	public Object vinnerEr() {
		return vinnerRute() != Brett.Rute.EMPTY ? motstanderAv(vinnerRute()) : null;
	}

	private Map<Object, Brett.Rute> getPlass() {
		return plass;
	}

	/**
	 * Forteller hvilken plass som blir tatt av spiller og okkuperer den.
	 */
	public void okkuperPlass(Object objekt) {
		fåLedigePlasser();
		plass.put(objekt, fåLedigePlasser()[0]);
	}

	/**
	 * Forteller hvilke plasser som ble tatt av spiller
	 */
	public Brett.Rute plassPosisjon(Object objekt) {
		return getPlass().get(objekt);
	}

	private Object motstanderAv(Brett.Rute rute) {
		for (Map.Entry<Object, Brett.Rute> entry : getPlass().entrySet()) {
			if (entry.getValue() == rute) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * Forteller hvilke plasser som er tatt av spiller
	 * Bruker fåLedigePlasser() metoden til å skille ut okkuperte plasser.
	 */
	private List<Brett.Rute> opptattePlasser() {
		return new ArrayList<>(getPlass().values());
	}

	private Brett.Rute[] fåLedigePlasser() {
		List<Brett.Rute> ruter = new ArrayList<>(Arrays.asList(Brett.spillbareRuter()));
		ruter.removeAll(opptattePlasser());
		return ruter.toArray(new Brett.Rute[0]);
	}

}