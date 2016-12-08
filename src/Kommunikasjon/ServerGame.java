package Kommunikasjon;

/**
 * Created by Bror on 08.12.2016.
 * A class to hold Kommunikasjon game information.
 */
public class ServerGame {
    boolean player1turn = false;
    boolean player2turn = false;



    public ServerGame()
    {
        /*Make each game start with player1Turn */
        player1Turn = true;
    }

    public void turnMade()
    {
        player1turn = !player1turn;
        player2turn = !player2turn;
    }

    public boolean isPlayer1Turn()
    {return player1Turn;

    }

    public booolean isPlayer2Turn()
    {return !isPlayer1Turn();

    }


}
