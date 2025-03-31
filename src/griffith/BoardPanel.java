package griffith;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	private static final int BOARD_SIZE = 8;
	private static final int SQUARE_SIZE = 80;
	 @Override
	 protected
	   void paintComponent(Graphics g) {
	     super.paintComponent(g);
	     
	     // Draw board squares
	     for (int row = 0; row < BOARD_SIZE; row++) {
	         for (int col = 0; col < BOARD_SIZE; col++) {
	             // Set color based on position
	             g.setColor(getSquareColor(row, col));
	             // Draw the square
	             g.fillRect(col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
	         }
	     }
	 }
	 
	 public Color  getSquareColor(int row, int column) {
		 
		    if ((row + column) % 2 == 0) {
		            return Color.WHITE;
		        } else {
		            return Color.BLACK;
		        }
		    
		  }
	 
}
	

