package serverKlient;

import spill.*;

import java.net.ServerSocket;

/**
 * Created by mariuswetterlin on 13.12.2016.
 */
public class ServerGame {


	/*public ServerGame(Spiller1 spiller1, Rute rute1, Spiller2 spiller2, Rute rute2, ServerSocket serverSocket) {

	}*/

	/**
	 * This class will have to contain Board information. On this rute the server will keep current rute.
	 * Brett is JFrame we don't need frame functionality in Board. Check Rute, that is the squars of the rute.
	 * THat is it Rute has methods like check if occupied, get coccupant, etc.
	 * */

	public class Game {
		Rute rute;
		public int x = Rute.getRad();
		int y = Rute.getKolonne();
		public Game() {
			rute = new Rute(Rute.bakgrunnsFarge.MORK, x, y); //We could use Rute methods just not rute as it is . But it 's okey neverthelesss.
		}

	}

	public static class Board {
		public enum bakgrunnsFarge {
			LYS, MORK, NIL
		}

		int width; int height;

		bakgrunnsFarge[] board = null;

		public Board(int sizex, int sizey) {
			board = new bakgrunnsFarge[sizex * sizey]; //now we have a rute
			width = 8; //var sizex
			height = 8;// var sizey

			for (int i = 0 ; i < width*height; i++)
			{
				board[i]=bakgrunnsFarge.NIL;//nothing is initially
			}
		}

		//put checker on rute at positio x,y
		public void put(int x, int y, bakgrunnsFarge bf)
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

/**
 _____________________________ (██)
 __________(█)_______________██████
 _________(███)___________ █████████
 ________(█████)________████████████
 ______ (███████)______ (░░░░░░░░░░░)
 _____(█████████)_____(░░░░█░░█░░░░)
 ____(██░░░░░░░██)___ (░░(░░░●░░░)░░░)
 _____▒░░█░░█░░▒____ (░░░(░░◡░░)░░░░)
 ____▒░░░░░░░░░░▒___ (░░░░░░░░░░░░░)
 ____▒░░█░░░█░░░▒___██(░░░░░░░░░)██
 ____▒░░░███░░░░▒___███(░░░░░░)████
 _____▒░░░░░░░░▒___████████████████
 _____██░░░░░░██___████████████████
 ____▒▒███████▒▒___███ █████████ ███
 ___▒░░░█████░░░▒__███ █████████ ███
 _▒░▒░░░███░░░▒░▒__███ █████████ ███
 _▒░░▒░░███░░▒░░▒_ ███ █████████ ███
 _▒░░░▒░███░▒░░░▒_ (░░) █████████_(░░)
 __▒░░▒░███░▒░░▒_______█████████__(██)
 _▒▒▒▒░░███░░▒▒▒▒_____█████████__/▓▓▓\
 _▒░░░░░░░░░░░░░▒____ ████__████▓▓▓▓▓▓)
 ▒░░░░░░░░░░░░░░░▒___████__████▓▓▓▓▓▓▓)
 ▒░░░░░░░░░░░░░░░▒___████__████▓▓▓▓▓▓▓)
 ▒░░░░░░░░░░░░░░░▒__(░░░░)_(░░░░)▓▓▓▓▓▓▓)
 ▒░░░░░░░░░░░░░░░▒___████__████▓▓▓▓▓▓▓▓)
 _▒░░░░░░░░░░░░░▒____ ████__████▓▓▓▓▓▓▓)
 __▒▒▒▒▒▒▒▒▒▒▒▒▒______████__████▓▓▓▓▓▓)
 */
