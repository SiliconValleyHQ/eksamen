package game;

import java.util.*;

public class Game {

	private Map<Object, Board.Tile> seats = new HashMap<>();

	private Board.Tile winningTile() {
		return Board.Tile.EMPTY;
	}

	public Object winnerIs() {
		return winningTile() != Board.Tile.EMPTY ? occuppantOf(winningTile()) : null;
	}

	private Map<Object, Board.Tile> getSeats() {
		return seats;
	}

	public void occupySeat(Object object) {
		getFreeSeats();
		seats.put(object, getFreeSeats()[0]); //put to first free seat
	}

	/*
	 * Tells which seat is occupied by Player
	 */
	public Board.Tile seatName(Object object) {
		return getSeats().get(object);
	}

	private Object occuppantOf(Board.Tile tile) {
		for (Map.Entry<Object, Board.Tile> entry : getSeats().entrySet()) {
			if (entry.getValue() == tile) {
				return entry.getKey();
			}
		}
		return null;
	}

	private List<Board.Tile> occupiedSeats() {
		return new ArrayList<>(getSeats().values());
	}

	private Board.Tile[] getFreeSeats() {
		List<Board.Tile> tiles = new ArrayList<>(Arrays.asList(Board.playableTiles()));
		tiles.removeAll(occupiedSeats());
		return tiles.toArray(new Board.Tile[0]);
	}

}