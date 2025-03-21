package griffith;

import javax.swing.JFrame;

public class Renderer {
	
	private JFrame window;
	private Board board;
	
	public void Setup(String title, int width, int heigth) {
		window = new JFrame(title);
		window.setTitle(title);
		window.setSize(width, heigth);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		board = new Board();
		window.add(board);
		window.setVisible(true);
	}
	
	public void Render() {
		board.repaint();
	}
}
