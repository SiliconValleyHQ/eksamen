package spill;

public class Trekk { //if you want to see what i did here let me know ;)

	private int fraX;
	private int fraY;
	private int tilX;
	private int tilY;

	/**
	 * Forventer string i format "x,y".
	 *
	 * @param fra
	 * @param til
	 */
	public Trekk(String fra, String til) {
		fraX = Integer.valueOf(fra.split(",")[0]);
		fraY = Integer.valueOf(fra.split(",")[1]);
		tilX = Integer.valueOf(til.split(",")[0]);
		tilY = Integer.valueOf(til.split(",")[1]);
	}

	public int getFraX() {
		return fraX;
	}

	public void setFraX(int fraX) {
		this.fraX = fraX;
	}

	public int getFraY() {
		return fraY;
	}

	public void setFraY(int fraY) {
		this.fraY = fraY;
	}

	public int getTilX() {
		return tilX;
	}

	public void setTilX(int tilX) {
		this.tilX = tilX;
	}

	public int getTilY() {
		return tilY;
	}

	public void setTilY(int tilY) {
		this.tilY = tilY;
	}

	public String toString2() {
		return String.format("%d,%d %d,%d", fraX, fraY, tilX, tilY);
	}

	public String toString() {
		return String.format("Et trekk fra [%d,%d] til [%d,%d].", fraX, fraY, tilX, tilY);
	}

}