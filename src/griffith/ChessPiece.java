package griffith;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.*;
import java.awt.event.*;

import java.awt.*;
import java.io.File;

public abstract class ChessPiece {
	protected boolean isWhite = true;
	protected Board board;
	protected Image sprite;
	
	public JButton button;
	
	private int initialX = 0;
	private int initialY = 0;
	
	ChessPiece(Board board ,boolean isWhite) {
		this.board = board;
		this.isWhite = isWhite;
	}

	ChessPiece(Board board ) {
		this.board = board;
	}
	
	 protected void loadImage(String imageName) {
	        try {  
	            String projectRoot = new File(getClass().getProtectionDomain()
	                    .getCodeSource().getLocation().toURI()).getParentFile().getPath();
	            String imagePath = projectRoot + "/src/res/" + imageName;
	            
	            File imageFile = new File(imagePath);
	            if (!imageFile.exists()) {
	                System.err.println("ERROR: Image file not found: " + imagePath);
	                return;
	            }
 
	            sprite = ImageIO.read(imageFile)
	                    .getScaledInstance(board.getSquareSize(), board.getSquareSize(), Image.SCALE_SMOOTH);
	            
	            if (sprite == null) {
	                System.err.println("ERROR: Failed to load image: " + imagePath);
	            } else {
	                System.out.println("Successfully loaded image for " + (isWhite ? "white" : "black") + " " );
	            }
	        } catch (Exception e) {
	            System.err.println("ERROR loading image: " + imageName);
	            e.printStackTrace();
	        }
	    }
	    

	abstract public String getValidMoves();
	
	abstract public boolean isMoveValid(int x, int y);
	
	  public void draw(JFrame panel) {
		  JButton pieceButton = new JButton();
		  button = pieceButton;
		  
			if (sprite != null) {
				
	            // Find position in board array
	            for (int row = 0; row < board.getBoredSize(); row++) {
	                for (int col = 0; col < board.getBoredSize(); col++) {
	                    if (board.getBoard()[row][col] == this) {
	                        int xPos = col * board.getSquareSize();
	                        int yPos = row * board.getSquareSize();
	                        
	                        pieceButton.setBounds(xPos, yPos, 80, 80);
	                        pieceButton.setIcon(new ImageIcon(sprite));
	                        pieceButton.setOpaque(true);
	                        
	                        initialX = xPos;
	                        initialY = yPos;

	                        pieceButton.addMouseMotionListener(new MouseMotionAdapter() {
	                            public void mouseDragged(MouseEvent e) {
	                            	if (!isWhite) {
	                            		return;
	                            	}
	                                int newX = pieceButton.getX() + e.getX() - 40;
	                                int newY = pieceButton.getY() + e.getY() - 40;
	                                pieceButton.setLocation(newX, newY);
	                            }
	                        });
	                        
	                        ChessPiece thisPiece = this;
	                        
	                        pieceButton.addMouseListener(new MouseAdapter() {
	                            public void mouseReleased(MouseEvent e) {
	                            	if (!isWhite) {
	                            		return;
	                            	}
	                                int newX = pieceButton.getX() + 40;
	                                int newY = pieceButton.getY() + 40;
	                                newX = (int) (((newX / 80)) * 80);
	                                newY = (int) (((newY / 80)) * 80);

	                                if(isMoveValid(newX / 80, board.getBoard().length - 1 - newY / 80)) {
	                                	pieceButton.setLocation(newX, newY);
	                                	board.movePiece(thisPiece, newX / 80, board.getBoard().length - 1 - newY / 80);
	                                	initialX = newX;
	                                	initialY = newY;

										int[] moves = GameLogic.getComputerMove(board);
                                        ChessPiece enemyPiece = board.getPiece(moves[0], moves[1]);
                                        board.movePiece(enemyPiece, moves[2], moves[3]);
                                        enemyPiece.button.setLocation(moves[2] * 80, (board.getBoard().length - 1 - moves[3]) * 80);
	                                }
	                                else {
	                                	pieceButton.setLocation(initialX, initialY);
	                                }
	                                
	                            }
	                        });
	                        
	                        panel.add(pieceButton);
	                        return;  // Exit after drawing once
	                    }
	                }
	            }
	        } else {
	            System.err.println("ERROR: No sprite loaded for " + (isWhite ? "white" : "black") );
	        }
	
}
}
