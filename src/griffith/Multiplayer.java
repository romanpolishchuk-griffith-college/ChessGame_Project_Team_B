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

    protected void startGame() {
        ChessBoard board = new ChessBoard();
        GameLogic logic = new GameLogic();

    }

   
    private void handleComputerMove() {
        // Computer makes a move
        int[] move = gameLogic.getComputerMove(chessBoard); // Get a move from the computer
        if (move != null) {
            gameLogic.executeMove(chessBoard, move[0], move[1], move[2], move[3]); // Execute the move
            chessBoard.repaint(); // Update the board
            isWhiteTurn = true; // Switch back to player's turn

            // Check if the game is over
            if (gameLogic.isGameOver()) {
                JOptionPane.showMessageDialog(null, "Game Over! Black (Computer) wins!");
            }
        }
    }
}
