package spillLogikk;

/**
 * Created by Bror on 30.11.2016.
 */
public class SpillData {

	private static final int rodSpiller = 1;
	private static final int svartSpiller = 2;
	public int[][] spillBrett;
	private int aktivSpiller;

	/*
	* Ikke fullstending, så gir feilmeldinger.. Ikke farlig
	*/
	public SpillData() {
		spillBrett = new int[8][8];
		startNyttSpill();
	}

	/*
	* Ikke fullstending, så gir feilmeldinger.. Ikke farlig
	*/
	public void startNyttSpill() {

		SpillRegler settOppNyttSpill = new SpillRegler();
		//settOppNyttSpill.settOppNyttSpill();

		aktivSpiller = rodSpiller;
		SpillRegler alleLovligeTrekk = new SpillRegler();
		//muligeTrekk = alleLovligeTrekk(aktivSpiller);
	}

}
