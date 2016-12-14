package game;

import java.util.Arrays;

public class Board {

	private int width;
	private int height;
	private Tile[] board;

	public enum Tile {
		WHITE, BLACK, EMPTY
	}

	static Tile[] playableTiles() {
		return new Tile[]{Tile.WHITE, Tile.BLACK};
	}

	private void initBoard() {
		board = new Board.Tile[getWidth() * getHeight()];
		Arrays.fill(board, Tile.EMPTY);
	}

	private Tile[] getBoard() {
		return board;
	}


	public void setBoard(Tile[] board) {
		this.board = board;
	}

	private int getWidth() {
		return width;
	}

	void setWidth(int width) {
		this.width = width;
	}

	private int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}


	private Board(int sizex, int sizey) {
		this.width = sizex;
		this.height = sizey;
		initBoard();
	}

	Board() {
		this(8, 8);
	}

	private Tile getTile(int x, int y) {
		return getBoard()[getWidth() * x + y];
	}

	void setTile(int x, int y, Tile tile) {
		board[getWidth() * x + y] = tile;
	}

	boolean isOccupied(int x, int y) {
		return !isEmpty(x, y);
	}

	private boolean isEmpty(int x, int y) {
		return getTile(x, y).equals(Tile.EMPTY);
	}

}