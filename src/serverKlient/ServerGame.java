package serverKlient;

/**
 * Created by mariuswetterlin on 13.12.2016.
 */
public class ServerGame
{
	public static class Game  //this class will have to contain Board information. On this board the server will keep current board.
	            // Brett is JFrame we don't need frame functionality in Board. Check Rute, that is the squars of the board.
	//THat is it Rute has methods like check if occupied, get coccupant, etc.
	{
		Board board;

		public Game()
		{
			board = new Board(); //We could use rutte methods just not rute as it is . But it 's okey neverthelesss.
		}

	}

	public static class Board
	{
		public enum bakgrunnsFarge {
			LYS, MORK, NIL
		}

		int width; int height;

		bakgrunnsFarge[] board = null;

		public Board(int sizex, int sizey)
		{
			board = new bakgrunnsFarge[sizex * sizey]; //now we have a board
			width = sizex;
			height = sizey;

			for (int i = 0 ; i < width*height; i++)
			{
				board[i]=bakgrunnsFarge.NIL;//nothing is initially
			}
		}

		public void put(int x, int y, bakgrunnsFarge bf)//put checker on board at positio x,y
		{
			board[width*y+x] = bf;
		}

		public bakgrunnsFarge get(int x, int y)
		{
			return board[width*y+x];
		}

		public boolean isOccupied(int x, int y)
		{
			return get(x,y) == bakgrunnsFarge.NIL;
		}

	}


}
