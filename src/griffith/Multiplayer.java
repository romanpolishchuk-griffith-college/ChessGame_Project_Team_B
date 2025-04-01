package griffith;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class Multiplayer  {
    private ChessBoard chessBoard;
    private GameLogic gameLogic;
    private boolean isWhiteTurn; // True for white's turn, false for black's turn (computer)

    public Multiplayer() {
        // Initialize components
        chessBoard = new ChessBoard();
        gameLogic = new GameLogic();
        isWhiteTurn = true; // White starts the game
    }

}
