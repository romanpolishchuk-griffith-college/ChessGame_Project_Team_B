package griffith;

import javax.swing.*;

public class Renderer {
	
	private JFrame window;
	private Board board;
	
	public void Setup(String title, int width, int heigth) {
		window = new JFrame(title);
		window.setSize(width, heigth);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		board = new Board(true);
		window.add(board);
		window.setVisible(true);
	}
	
	public void RenderGame() {
		board.repaint();

		System.out.println("Rendering frame...");
	}
	
	public void RenderMenu() {
		
	}
	
//	//initialising ChessGameUI
//	 public static void main(String[] args) {
//	        SwingUtilities.invokeLater(() -> {//Ensuring the graphics interface runs
//	            Renderer renderer = new Renderer();
//	            renderer.Setup("Chess Game", 800, 600);//Title window
//	            new ChessGameUI(); //Will start ChessGameUI
//	        });
//	    }



	}
