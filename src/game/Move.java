package game;

public class Move { //if you want to see what i did here let me know ;)

	private int xFrom;
	private int yFrom;
	private int xTo;
	private int yTo;

	public int getxFrom() {
		return xFrom;
	}

	public void setxFrom(int xFrom) {
		this.xFrom = xFrom;
	}

	public int getyFrom() {
		return yFrom;
	}

	public void setyFrom(int yFrom) {
		this.yFrom = yFrom;
	}

	public int getxTo() {
		return xTo;
	}

	public void setxTo(int xTo) {
		this.xTo = xTo;
	}

	public int getyTo() {
		return yTo;
	}

	public void setyTo(int yTo) {
		this.yTo = yTo;
	}

	/**
	 * Expecting strings in format "x,y" (no quotes).
	 *
	 * @param from
	 * @param to
	 */
	public Move(String from, String to) {
		xFrom = Integer.valueOf(from.split(",")[0]);
		yFrom = Integer.valueOf(from.split(",")[1]);
		xTo = Integer.valueOf(to.split(",")[0]);
		yTo = Integer.valueOf(to.split(",")[1]);
	}

	public String toString2() {
		return String.format("%d,%d %d,%d", xFrom, yFrom, xTo, yTo);
	}

	public String toString() {
		return String.format("A move from [%d,%d] to [%d,%d].", xFrom, yFrom, xTo, yTo);
	}

}