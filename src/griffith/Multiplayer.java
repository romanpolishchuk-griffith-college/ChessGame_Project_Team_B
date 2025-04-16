package griffith;

import javax.swing.*;
import java.awt.event.MouseEvent;

// Represents a multiplayer game.
public class Multiplayer {

    private Board board;
    private GameLogic gameLogic;
    private Renderer renderer; // Renderer for the game
    boolean isWhiteTurn; // True for white's turn, false for black's turn (computer)

    // Constructor for the multiplayer game.
    public Multiplayer() {

        // Initialize components
        board = new Board();
        gameLogic = new GameLogic();
        isWhiteTurn = GameLogic.isPlayerWhite(); // Player's turn starts based on their color
    }

    // Starts the game.
    public void startGame() {

        //Set up the game window
        JFrame window = new JFrame("Chess vs Computer");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 800);
        window.add(board);
        window.setVisible(true);

        //Add mouse listener for player moves
        board.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (isWhiteTurn) {
                    handlePlayerMove(e);
                }
            }
        });
    }

    // Handles the player's move.
    private void handlePlayerMove(MouseEvent e) {

        // Get the clicked square
        int x = e.getX() / (board.getWidth() / 8);
        int y = e.getY() / (board.getHeight() / 8);

        // Get the piece at the clicked square
        ChessPiece selectedPiece = board.getPiece(x, y);

        // Ensure the selected piece belongs to the player
        if (selectedPiece == null || selectedPiece.isWhite() != GameLogic.isPlayerWhite()) {
            System.out.println("Invalid selection. You can only move your pieces.");
            return;
        }

        // Validate and execute the player's move
        if (gameLogic.isMoveValid(board, x, y, x, y, isWhiteTurn)) {
            gameLogic.executeMove(board, x, y, x, y);
            board.repaint(); // Update the board
            isWhiteTurn = false; // Switch to computer's turn

            if (gameLogic.isGameOver(board)) {
                JOptionPane.showMessageDialog(null, "Game Over! You win!");
                return;
            }

            handleComputerMove(); // Let the computer make its move
        } else {
            System.out.println("Invalid move. Try again.");
        }
    }

    // Pause the timer during the computer's move
    private void handleComputerMove() {
        if (renderer.countdownTimer != null) {
            renderer.countdownTimer.stop(); // Pause the timer
        }

        int[] move = GameLogic.getComputerMove(board);
        if (move != null) {
            gameLogic.executeMove(board, move[0], move[1], move[2], move[3]);
            board.repaint();
            isWhiteTurn = true;

            if (gameLogic.isGameOver(board)) {
                JOptionPane.showMessageDialog(null, "Game Over! Computer wins!");
            }
        }

        if (renderer.countdownTimer != null) {
            renderer.countdownTimer.start(); // Resume the timer
        }
    }

    public boolean getIsWhiteTurn() {
        return isWhiteTurn;
    }
}
