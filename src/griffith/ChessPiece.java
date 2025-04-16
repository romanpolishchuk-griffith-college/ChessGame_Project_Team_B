package griffith;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public abstract class ChessPiece {

    protected boolean isWhite = true;
    protected Board board;
    protected Image sprite;
    protected String pieceName;

    // The button for the piece
    public JButton button;

    // The initial x position of the piece
    private int initialX = 0;
    // The initial y position of the piece
    private int initialY = 0;

    // Constructor with specified board and color.
    ChessPiece(Board board, boolean isWhite, String pieceName) {
        this.board = board;
        this.isWhite = isWhite;
        this.pieceName = pieceName;
    }

    ChessPiece(Board board) {
        this.board = board;
    }

    protected void loadImage(String imageName) {
        try {

            // Load image
            ImageIcon icon = new ImageIcon(getClass().getResource("/res/" + imageName));

            // Scale it to fit the square
            sprite = icon.getImage().getScaledInstance(
                    board.getSquareSize(), board.getSquareSize(), Image.SCALE_SMOOTH
            );

            if (sprite != null) {
                System.out.println("Loaded image: " + pieceName + " (" + (isWhite ? "White" : "Black") + ")");

            } else {
                System.err.println("Failed to scale image: " + imageName);

            }
        } catch (Exception e) {

            System.err.println("Failed to load image for " + pieceName + ": /res/" + imageName);
            e.printStackTrace();

        }
    }


    // Returns the valid moves for the piece.
    abstract public String getValidMoves();

    // Returns true if the move is valid for the piece.
    abstract public boolean isMoveValid(int x, int y);

    //added a getter for the isWhite property
    public boolean isWhite() {
        return isWhite;
    }

    // Draws the piece on the board.
    public void draw(JFrame panel) {

        // Create a new button
        JButton piece = new JButton();

        // Set the button to the piece
        button = piece;

        if (sprite != null) {

            // Find position in board array
            for (int row = 0; row < board.getBoredSize(); row++) {

                for (int col = 0; col < board.getBoredSize(); col++) {

                    // If the piece is at the current position
                    if (board.getBoard()[row][col] == this) {

                        // Get the x position
                        int xPos = col * board.getSquareSize();

                        // Get the y position
                        int yPos = row * board.getSquareSize();

                        // Set the bounds of the piece
                        piece.setBounds(xPos, yPos, 80, 80);

                        // Set the icon of the piece
                        piece.setIcon(new ImageIcon(sprite));

                        // Set the piece to be opaque
                        piece.setOpaque(true);

                        // Set the initial x position
                        initialX = xPos;

                        // Set the initial y position
                        initialY = yPos;

                        // Add a mouse listener to the piece
                        ChessPiece thisPiece = this;

                        // Add a mouse listener to the piece
                        piece.addMouseListener(new MouseInputAdapter() {
                            // When the mouse is pressed
                            public void mousePressed(MouseEvent e) {
                                if (isWhite != GameLogic.isPlayerWhite()) {
                                    return;
                                }
                                int pieceStartPositionX = -1;
                                int pieceStartPositionY = -1;

                                for (int y = 0; y < board.getBoard().length; y++) {
                                    for (int x = 0; x < board.getBoard()[y].length; x++) {
                                        if (board.getPiece(x, y) == thisPiece) {
                                            pieceStartPositionX = x;
                                            pieceStartPositionY = y;
                                            break;
                                        }
                                    }
                                }

                                ChessPiece selectedPiece = board.getPiece(pieceStartPositionX, pieceStartPositionY);
                                if(selectedPiece.getValidMoves() != "") {
                                    BoardPanel.highlightedMoves = selectedPiece.getValidMoves().split(" ");
                                }

                                panel.revalidate();
                                panel.repaint();
                            }
                        });

                        // Add a mouse motion listener to the piece
                        piece.addMouseMotionListener(new MouseMotionAdapter() {

                            // When the mouse is dragged
                            public void mouseDragged(MouseEvent e) {
                                if (isWhite != GameLogic.isPlayerWhite()) {
                                    return;
                                }

                                // Get the new x position
                                int newX = piece.getX() + e.getX() - 40;

                                // Get the new y position
                                int newY = piece.getY() + e.getY() - 40;

                                // Set the new location of the piece
                                piece.setLocation(newX, newY);
                            }
                        });

                        // Add a mouse listener to the piece
                        piece.addMouseListener(new MouseAdapter() {

                            // When the mouse is released
                            public void mouseReleased(MouseEvent e) {

                                // Check if the piece belongs to the current player
                                if (isWhite != GameLogic.isPlayerWhite()) {
                                    System.out.println("You cannot move the opponent's pieces.");
                                    return;
                                }

                                BoardPanel.highlightedMoves = null;
                                panel.revalidate();
                                panel.repaint();

                                // Get the new x position
                                int newX = piece.getX() + e.getX();

                                // Get the new y position
                                int newY = piece.getY() + e.getY();

                                // Set the new location of the piece
                                newX = (int) (((newX / 80)) * 80);
                                newY = (int) (((newY / 80)) * 80);

                                System.out.println("X: " + newX / 80 + " " + (7 - newY / 80));

                                if (isMoveValid(newX / 80, 7 - newY / 80)) {
                                    piece.setLocation(newX, newY);
                                    board.movePiece(thisPiece, newX / 80, 7 - newY / 80);
                                    initialX = newX;
                                    initialY = newY;

                                    // Get the computer move
                                    int[] moves = GameLogic.getComputerMove(board);

                                    if (moves != null) {
                                        ChessPiece pieceMove = board.getPiece(moves[0], moves[1]);
                                        board.movePiece(pieceMove, moves[2], moves[3]);
                                        pieceMove.button.setLocation(moves[2] * 80, (7 - moves[3]) * 80);
                                        System.out.println(moves[0] + " " + moves[1] + " " + moves[2] + " " + moves[3]);
                                    }
                                } else {
                                    // If the move is not valid, reset the piece to its original position
                                    piece.setLocation(initialX, initialY);
                                }
                            }
                        });
                        // Add the piece to the panel
                        panel.add(piece);
                        return;  // Exit after drawing once
                    }
                }
            }
        } else {
            // Print an error message
            System.err.println("ERROR: No sprite loaded for " + (isWhite ? "white" : "black"));
        }

    }
}
