package griffith;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.*;
import java.awt.event.*;

import java.awt.*;
import java.io.File;

// Represents a chess piece.
public abstract class ChessPiece {
	protected boolean isWhite = true;
	protected Board board;
	protected Image sprite;
	
	// The button for the piece
	public JButton button;
	
	// The initial x position of the piece
	private int initialX = 0;
	// The initial y position of the piece
	private int initialY = 0;
	
	// Constructor with specified board and color.
	ChessPiece(Board board ,boolean isWhite) {
		this.board = board;
		this.isWhite = isWhite;
	}

	ChessPiece(Board board ) {
		this.board = board;
	}
	
	// Loads the image for the piece.
	protected void loadImage(String imageName) {
	        try {  
				// Get the project root
	            String projectRoot = new File(getClass().getProtectionDomain()
	                    .getCodeSource().getLocation().toURI()).getParentFile().getPath();
				// Get the image path
	            String imagePath = projectRoot + "/src/res/" + imageName;
				// Check if the image file exists
	            File imageFile = new File(imagePath);
				// If the image file does not exist, print an error message
	            if (!imageFile.exists()) {
	                System.err.println("ERROR: Image file not found: " + imagePath);
	                return;
	            }
				// Load the image
	            sprite = ImageIO.read(imageFile)
	                    .getScaledInstance(board.getSquareSize(), board.getSquareSize(), Image.SCALE_SMOOTH);
				// If the image is not loaded, print an error message
	            if (sprite == null) {
	                System.err.println("ERROR: Failed to load image: " + imagePath);
	            } else {
					// Print a success message
	                System.out.println("Successfully loaded image for " + (isWhite ? "white" : "black") + " " );
	            }
	        } catch (Exception e) {
				// Print an error message
	            System.err.println("ERROR loading image: " + imageName);
				// Print the stack trace
	            e.printStackTrace();
	        }
	    }
	    
	// Returns the valid moves for the piece.
	abstract public String getValidMoves();
	
	// Returns true if the move is valid for the piece.
	abstract public boolean isMoveValid(int x, int y);
	
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
	                        piece.addMouseListener(new MouseInputAdapter() {
	                            // When the mouse is pressed
	                            public void mousePressed(MouseEvent e) {
	                            }
	                        });

	                        // Add a mouse motion listener to the piece
	                        piece.addMouseMotionListener(new MouseMotionAdapter() {
	                            // When the mouse is dragged
	                            public void mouseDragged(MouseEvent e) {
	                                // Get the new x position
	                                int newX = piece.getX() + e.getX() - 40;
	                                // Get the new y position
	                                int newY = piece.getY() + e.getY() - 40;
	                                // Set the new location of the piece
	                                piece.setLocation(newX, newY);
	                            }
	                        });

	                        // Add a mouse listener to the piece
	                        ChessPiece thisPiece = this;
							// Add a mouse listener to the piece	
	                        piece.addMouseListener(new MouseAdapter() {
	                            // When the mouse is released
	                            public void mouseReleased(MouseEvent e) {
	                                // Get the new x position
	                                int newX = piece.getX() + e.getX();
	                                // Get the new y position
	                                int newY = piece.getY() + e.getY();
	                                // Set the new location of the piece
	                                newX = (int) (((newX / 80)) * 80);
	                                // Set the new location of the piece
	                                newY = (int) (((newY / 80)) * 80);
	                                System.out.println("X: " + newX / 80 + " " + (7 - newY / 80));
	                                if(isMoveValid(newX / 80, 7 - newY / 80)) {
	                                	piece.setLocation(newX, newY);
	                                	board.movePiece(thisPiece, newX / 80, 7 - newY / 80);
	                                	initialX = newX;
	                                	// Set the initial y position
	                                	initialY = newY;

										// Get the computer move
										int[] moves = GameLogic.getComputerMove(board);
										// Get the piece to move
                                        ChessPiece pieceMove = board.getPiece(moves[0], moves[1]);
										// Move the piece
                                        board.movePiece(pieceMove, moves[2], moves[3]);
										// Set the location of the piece
                                        pieceMove.button.setLocation(moves[2] * 80, (7-moves[3]) * 80);
										// Print the move
                                        System.out.println(moves[0] + " " + moves[1] + " " + moves[2] + " " + moves[3]);
	                                }
	                                // If the move is not valid, set the location of the piece to the initial location
	                                else {
	                                	piece.setLocation(initialX, initialY);
	                                }
	                                
	                            }
	                        });
	                        // Add the piece to the panel
	                        panel.add(piece);
//	                        g.drawImage(sprite, xPos, yPos, null);
	                        return;  // Exit after drawing once
	                    }
	                }
	            }
	        } else {
				// Print an error message
	            System.err.println("ERROR: No sprite loaded for " + (isWhite ? "white" : "black") );
	        }
	
}
}
