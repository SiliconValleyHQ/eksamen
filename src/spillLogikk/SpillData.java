package spillLogikk;

/**
 * Created by Bror on 30.11.2016.
 */
public class SpillData {

    public int[][] spillBrett;

    public SpillData() {
        spillBrett = new int[8][8];
        startNyttSpill();
    }

    public void startNyttSpill() {

        SpillRegler settOppNyttSpill = new SpillRegler();
        settOppNyttSpill.settOppNyttSpill();

    }

}
