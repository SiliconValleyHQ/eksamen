package spill;

import java.util.*;

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

	public void okkuperPlass(Object object) {
		fåLedigePlasser();
		plass.put(object, fåLedigePlasser()[0]); // plasser på første ledige sete
	}

	/**
	 * Forteller hvilke plasser som er tatt av spiller
	 */
	public Brett.Rute plassPosisjon(Object object) {
		return getPlass().get(object);
	}

	private Object motstanderAv(Brett.Rute rute) {
		for (Map.Entry<Object, Brett.Rute> entry : getPlass().entrySet()) {
			if (entry.getValue() == rute) {
				return entry.getKey();
			}
		}
		return null;
	}

	private List<Brett.Rute> opptattePlasser() {
		return new ArrayList<>(getPlass().values());
	}

	private Brett.Rute[] fåLedigePlasser() {
		List<Brett.Rute> ruter = new ArrayList<>(Arrays.asList(Brett.spillbareRuter()));
		ruter.removeAll(opptattePlasser());
		return ruter.toArray(new Brett.Rute[0]);
	}

}