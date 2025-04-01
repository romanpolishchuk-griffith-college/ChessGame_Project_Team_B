package griffith;

import java.awt.Component;

import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		
		JFrame window = new JFrame("ChessDreamApp");
		window.setTitle("ChessDream");
		window.setSize(800, 800);
		window.setVisible(true);

		// Create a new instance of the ChessBoard class
		ChessBoard board = new ChessBoard();
		window.add(board);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		

		

		Game game = new Game("Super Chess", 1080, 720);
		Game game = new Game("Super Chess", 820, 720);
		game.Run();

	}
}
