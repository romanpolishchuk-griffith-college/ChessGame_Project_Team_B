package griffith;

import javax.swing.*;

public class Renderer {
	
	private JFrame window;
	
	public void Setup(String title, int width, int heigth) {
		window = new JFrame(title);
		window.setSize(width, heigth);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Ensuring the application exits when the window is closed.
		window.setVisible(true);
	}
	
	public void Render() {
		 System.out.println("Rendering frame...");//
	}
	//initialising ChessGameUI
	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {//Ensuring the graphics interface runs
	            Renderer renderer = new Renderer();
	            renderer.Setup("Chess Game", 800, 600);//Title window
	            new ChessGameUI(); //Will start ChessGameUI
	        });
	    }
	}
