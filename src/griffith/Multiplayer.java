package griffith;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class Multiplayer {
    private ChessBoard chessBoard;
    private GameLogic gameLogic;
    private boolean isWhiteTurn; // True for white's turn, false for black's turn (computer)

    public Multiplayer() {
        // Initialize components
        chessBoard = new ChessBoard();
        gameLogic = new GameLogic();
        isWhiteTurn = true; // White starts the game
    }

    public void startGame() {
        // Set up the game window
        JFrame window = new JFrame("Chess vs Computer");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 800);
        window.add(chessBoard);
        window.setVisible(true);

        // Add mouse listener for player moves
        chessBoard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (isWhiteTurn) {
                    handlePlayerMove(e);
                }
            }
        });
    }

    private void handlePlayerMove(MouseEvent e) {
        
        // Get the clicked square
        int x = e.getX() / (chessBoard.getWidth() / 8);
        int y = e.getY() / (chessBoard.getHeight() / 8);

        // Validate and execute the player's move
        if (gameLogic.isMoveValid(chessBoard, x, y, isWhiteTurn)) {
            gameLogic.executeMove(chessBoard, x, y);
            chessBoard.repaint(); // Update the board
            isWhiteTurn = false; // Switch to computer's turn

            // Check if the game is over
            if (gameLogic.isGameOver(chessBoard)) {
                JOptionPane.showMessageDialog(null, "Game Over! White wins!");
                return;
            }

            // Let the computer make its move
            handleComputerMove();
        } else {
            System.out.println("Invalid move. Try again.");
        }
    }

    private void handleComputerMove() {
        // Computer makes a move
        int[] move = gameLogic.getComputerMove(chessBoard); // Get a move from the computer
        if (move != null) {
            gameLogic.executeMove(chessBoard, move[0], move[1], move[2], move[3]); // Execute the move
            chessBoard.repaint(); // Update the board
            isWhiteTurn = true; // Switch back to player's turn

            // Check if the game is over
            if (gameLogic.isGameOver(chessBoard)) {
                JOptionPane.showMessageDialog(null, "Game Over! Black (Computer) wins!");
            }
        }
    }
}
