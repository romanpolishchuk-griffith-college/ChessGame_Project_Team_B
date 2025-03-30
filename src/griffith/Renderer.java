package griffith;

import javax.swing.JFrame;

public class Renderer {
	
	private JFrame window;
	
	public void Setup(String title, int width, int heigth) {
		window = new JFrame(title);
		window.setSize(width, heigth);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Ensuring the application exits when the window is closed.
		window.setVisible(true);
	}
	
	public void Render() {

	}
}
