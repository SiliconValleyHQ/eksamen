package test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Bror on 01.12.2016.
 */
public class DamSpill implements ActionListener {

    // This canvas displays a 160-by-160 checkerboard pattern with
    // a 2-pixel black border.  It is assumed that the size of the
    // canvas is set to exactly 164-by-164 pixels.  This class does
    // the work of letting the users play checkers, and it displays
    // the checkerboard.

    Button resignButton;   // Current player can resign by clicking this button.
    Button newGameButton;  // This button starts a new game.  It is enabled only
    //     when the current game has ended.

    Label message;   // A label for displaying messages to the user.

    CheckersData board;  // The data for the checkers board is kept here.
    //    This board is also responsible for generating
    //    lists of legal moves.

    boolean gameInProgress; // Is a game currently in progress?

   /* The next three variables are valid only when the game is in progress. */

    int currentPlayer;      // Whose turn is it now?  The possible values
    //    are CheckersData.RED and CheckersData.BLACK.
    int selectedRow, selectedCol;  // If the current player has selected a piece to
    //     move, these give the row and column
    //     containing that piece.  If no piece is
    //     yet selected, then selectedRow is -1.
    FlyttBrikke[] legalMoves;  // An array containing the legal moves for the
    //   current player.
    SpillData cSpilldata = new SpillData();

    public DamSpill() {
        resignButton = new Button("Resign");
        resignButton.addActionListener(this);
        newGameButton.addActionListener(this);
        message = new Label("",Label.CENTER);
        board = new CheckersData();
        cSpilldata.doNewGame();
    }


    public Dimension getPreferredSize() {
        // Specify desired size for this component.  Note:
        // the size MUST be 164 by 164.
        return new Dimension(164, 164);
    }


    public Dimension getMinimumSize() {
        return new Dimension(164, 164);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
