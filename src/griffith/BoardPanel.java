package griffith;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	private static final int BOARD_SIZE = 8;
	private static final int SQUARE_SIZE = 80;

	public static String[] highlightedMoves;

	@Override
	protected void paintComponent(Graphics g) {
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

		if (highlightedMoves != null) {
			for (int i = 0; i < highlightedMoves.length; i++) {
				g.setColor(Color.BLUE);
				g.fillOval(Integer.parseInt(highlightedMoves[i].charAt(0) + "") * 80 + 40 - 10, ( 7 - Integer.parseInt(highlightedMoves[i].charAt(2) + "")) * 80 + 40 - 10, 20, 20);
			}
		}
	}

	public Color getSquareColor(int row, int column) {

		if ((row + column) % 2 == 0) {
			return Color.WHITE;
		} else {
			return Color.BLACK;
		}

	}
}
	

