package test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Bror on 01.12.2016.
 */
public class SpillData extends Canvas implements ActionListener, MouseListener {

    SpillRegler cSpillregler;
    DamSpill cDamSpill;
    private FlyttBrikke[] legalMoves;
    private int selectedRow = -1;
    private int selectedCol = -1;

    public SpillData() {
        board = new int[8][8];
        cSpillregler.setUpGame();
        legalMoves = cSpillregler.getLegalMoves(currentPlayer);
    }

    public static final int
            EMPTY = 0,
            RED = 1,
            RED_KING = 2,
            BLACK = 3,
            BLACK_KING = 4;

    public int[][] board;  // board[r][c] is the contents of row r, column c.

    public int currentPlayer = 0;

    public void doNewGame() {
        // Begin a new game.
        if (cDamSpill.gameInProgress == true) {
            // This should not be possible, but it doens't
            // hurt to check.
            cDamSpill.message.setText("Finish the current game first!");
            return;
        }
        cSpillregler.setUpGame();   // Set up the pieces.
        currentPlayer = RED;   // RED moves first.
        FlyttBrikke[] legalMoves = cSpillregler.getLegalMoves(RED);  // Get RED's legal moves.
        int selectedRow = -1;   // RED has not yet selected a piece to move.
        cDamSpill.message.setText("Red:  Make your move.");
        boolean gameInProgress = true;
        cDamSpill.resignButton.setEnabled(true);
        repaint();
    }

    public void actionPerformed(ActionEvent evt) {
        // Respond to user's click on one of the two buttons.
        Object src = evt.getSource();
        if (src == cDamSpill.resignButton)
            doResign();
    }

    void doResign() {
        // Current player resigns.  Game ends.  Opponent wins.
        if (cDamSpill.gameInProgress == false) {
            cDamSpill.message.setText("There is no game in progress!");
            return;
        }
        if (currentPlayer == RED)
            gameOver("RED resigns.  BLACK wins.");
        else
            gameOver("BLACK resigns.  RED winds.");
    }

    void gameOver(String str) {
        // The game ends.  The parameter, str, is displayed as a cDamSpill.message
        // to the user.  The states of the buttons are adjusted so playes
        // can start a new game.
        cDamSpill.message.setText(str);
        cDamSpill.newGameButton.setEnabled(true);
        cDamSpill.resignButton.setEnabled(false);
        cDamSpill.gameInProgress = false;
    }

    void doClickSquare(int row, int col) {
        // This is called by mousePressed() when a player clicks on the
        // square in the specified row and col.  It has already been checked
        // that a game is, in fact, in progress.

      /* If the player clicked on one of the pieces that the player
         can move, mark this row and col as selected and return.  (This
         might change a previous selection.)  Reset the cDamSpill.message, in
         case it was previously displaying an error cDamSpill.message. */

        for (int i = 0; i < legalMoves.length; i++)
            if (legalMoves[i].fromRow == row && legalMoves[i].fromCol == col) {
                selectedRow = row;
                selectedCol = col;
                if (currentPlayer == RED)
                    cDamSpill.message.setText("RED:  Make your move.");
                else
                    cDamSpill.message.setText("BLACK:  Make your move.");
                repaint();
                return;
            }

      /* If no piece has been selected to be moved, the user must first
         select a piece.  Show an error cDamSpill.message and return. */

        if (selectedRow < 0) {
            cDamSpill.message.setText("Click the piece you want to move.");
            return;
        }

      /* If the user clicked on a squre where the selected piece can be
         legally moved, then make the move and return. */

        for (int i = 0; i < legalMoves.length; i++)
            if (legalMoves[i].fromRow == selectedRow && legalMoves[i].fromCol == selectedCol
                    && legalMoves[i].toRow == row && legalMoves[i].toCol == col) {
                doMakeMove(legalMoves[i]);
                return;
            }

      /* If we get to this point, there is a piece selected, and the square where
         the user just clicked is not one where that piece can be legally moved.
         Show an error cDamSpill.message. */

        cDamSpill.message.setText("Click the square you want to move to.");

    }  // end doClickSquare()

    void doMakeMove(FlyttBrikke move) {
        // Thiis is called when the current player has chosen the specified
        // move.  Make the move, and then either end or continue the game
        // appropriately.

        makeMove(move);

      /* If the move was a jump, it's possible that the player has another
         jump.  Check for legal jumps starting from the square that the player
         just moved to.  If there are any, the player must jump.  The same
         player continues moving.
      */

        if (move.isJump()) {
            legalMoves = cSpillregler.getLegalMoves(currentPlayer,move.toRow,move.toCol);
            if (legalMoves != null) {
                if (currentPlayer == RED)
                    cDamSpill.message.setText("RED:  You must continue jumping.");
                else
                    cDamSpill.message.setText("BLACK:  You must continue jumping.");
                selectedRow = move.toRow;  // Since only one piece can be moved, select it.
                selectedCol = move.toCol;
                repaint();
                return;
            }
        }

      /* The current player's turn is ended, so change to the other player.
         Get that player's legal moves.  If the player has no legal moves,
         then the game ends. */

        if (currentPlayer == RED) {
            currentPlayer = BLACK;
            legalMoves = cSpillregler.getLegalMoves(currentPlayer);
            if (legalMoves == null)
                gameOver("BLACK has no moves.  RED wins.");
            else if (legalMoves[0].isJump())
                cDamSpill.message.setText("BLACK:  Make your move.  You must jump.");
            else
                cDamSpill.message.setText("BLACK:  Make your move.");
        }
        else {
            currentPlayer = RED;
            legalMoves = cSpillregler.getLegalMoves(currentPlayer);
            if (legalMoves == null)
                gameOver("RED has no moves.  BLACK wins.");
            else if (legalMoves[0].isJump())
                cDamSpill.message.setText("RED:  Make your move.  You must jump.");
            else
                cDamSpill.message.setText("RED:  Make your move.");
        }

      /* Set selectedRow = -1 to record that the player has not yet selected
          a piece to move. */

        selectedRow = -1;

      /* As a courtesy to the user, if all legal moves use the same piece, then
         select that piece automatically so the use won't have to click on it
         to select it. */

        if (legalMoves != null) {
            boolean sameStartSquare = true;
            for (int i = 1; i < legalMoves.length; i++)
                if (legalMoves[i].fromRow != legalMoves[0].fromRow
                        || legalMoves[i].fromCol != legalMoves[0].fromCol) {
                    sameStartSquare = false;
                    break;
                }
            if (sameStartSquare) {
                selectedRow = legalMoves[0].fromRow;
                selectedCol = legalMoves[0].fromCol;
            }
        }

      /* Make sure the board is redrawn in its new state. */

        repaint();

    }  // end doMakeMove();



    public void mousePressed(MouseEvent evt) {
        // Respond to a user click on the board.  If no game is
        // in progress, show an error cDamSpill.message.  Otherwise, find
        // the row and column that the user clicked and call
        // doClickSquare() to handle it.
        if (cDamSpill.gameInProgress == false)
            cDamSpill.message.setText("Click \"New Game\" to start a new game.");
        else {
            int col = (evt.getX() - 2) / 20;
            int row = (evt.getY() - 2) / 20;
            if (col >= 0 && col < 8 && row >= 0 && row < 8)
                doClickSquare(row,col);
        }
    }

    public int pieceAt(int row, int col) {
        // Return the contents of the square in the specified row and column.
        return board[row][col];
    }


    public void setPieceAt(int row, int col, int piece) {
        // Set the contents of the square in the specified row and column.
        // piece must be one of the constants EMPTY, RED, BLACK, RED_KING,
        // BLACK_KING.
        board[row][col] = piece;
    }

    public void makeMove(FlyttBrikke move) {
        // Make the specified move.  It is assumed that move
        // is non-null and that the move it represents is legal.
        makeMove(move.fromRow, move.fromCol, move.toRow, move.toCol);
    }


    public void makeMove(int fromRow, int fromCol, int toRow, int toCol) {
        // Make the move from (fromRow,fromCol) to (toRow,toCol).  It is
        // assumed that this move is legal.  If the move is a jump, the
        // jumped piece is removed from the board.  If a piece moves
        // the last row on the opponent's side of the board, the
        // piece becomes a king.
        board[toRow][toCol] = board[fromRow][fromCol];
        board[fromRow][fromCol] = EMPTY;
        if (fromRow - toRow == 2 || fromRow - toRow == -2) {
            // The move is a jump.  Remove the jumped piece from the board.
            int jumpRow = (fromRow + toRow) / 2;  // Row of the jumped piece.
            int jumpCol = (fromCol + toCol) / 2;  // Column of the jumped piece.
            board[jumpRow][jumpCol] = EMPTY;
        }
        if (toRow == 0 && board[toRow][toCol] == RED)
            board[toRow][toCol] = RED_KING;
        if (toRow == 7 && board[toRow][toCol] == BLACK)
            board[toRow][toCol] = BLACK_KING;
    }


    public void mouseReleased(MouseEvent evt) { }
    public void mouseClicked(MouseEvent evt) { }
    public void mouseEntered(MouseEvent evt) { }
    public void mouseExited(MouseEvent evt) { }

}
