package griffith;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.*;
import java.awt.event.*;

import java.awt.*;
import java.io.File;

public abstract class ChessPiece {
	protected boolean isWhite;
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
		  JButton piece = new JButton();
		  button = piece;
		  
			if (sprite != null) {
				
	            // Find position in board array
	            for (int row = 0; row < board.getBoredSize(); row++) {
	                for (int col = 0; col < board.getBoredSize(); col++) {
	                    if (board.getBoard()[row][col] == this) {
	                        int xPos = col * board.getSquareSize();
	                        int yPos = row * board.getSquareSize();
	                        
	                        piece.setBounds(xPos, yPos, 80, 80);
	                        piece.setIcon(new ImageIcon(sprite));
	                        piece.setOpaque(true);
	                        
	                        initialX = xPos;
	                        initialY = yPos;
	                        
	                        piece.addMouseListener(new MouseInputAdapter() {
	                            public void mousePressed(MouseEvent e) {
	                            }
	                        });

	                        piece.addMouseMotionListener(new MouseMotionAdapter() {
	                            public void mouseDragged(MouseEvent e) {
	                                int newX = piece.getX() + e.getX() - 40;
	                                int newY = piece.getY() + e.getY() - 40;
	                                piece.setLocation(newX, newY);
	                            }
	                        });
	                        
	                        ChessPiece thisPiece = this;
	                        
	                        piece.addMouseListener(new MouseAdapter() {
	                            public void mouseReleased(MouseEvent e) {
	                                int newX = piece.getX() + e.getX();
	                                int newY = piece.getY() + e.getY();
	                                newX = (int) (((newX / 80)) * 80);
	                                newY = (int) (((newY / 80)) * 80);
	                                System.out.println("X: " + newX / 80 + " " + (7 - newY / 80));
	                                if(isMoveValid(newX / 80, 7 - newY / 80)) {
	                                	piece.setLocation(newX, newY);
	                                	board.movePiece(thisPiece, newX / 80, 7 - newY / 80);
	                                	initialX = newX;
	                                	initialY = newY;
	                                }
	                                else {
	                                	piece.setLocation(initialX, initialY);
	                                }
	                                
	                            }
	                        });
	                        
	                        panel.add(piece);
//	                        g.drawImage(sprite, xPos, yPos, null);
	                        return;  // Exit after drawing once
	                    }
	                }
	            }
	        } else {
	            System.err.println("ERROR: No sprite loaded for " + (isWhite ? "white" : "black") );
	        }
	
}
	  
	  public boolean isWhite() {
		  return isWhite;
	  }
	  }
