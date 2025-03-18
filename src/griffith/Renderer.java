package griffith;

import javax.swing.JFrame;

public class Renderer {
	
	private JFrame window;
	
	public void Setup(String title, int width, int heigth) {
		window = new JFrame(title);
		window.setTitle(title);
		window.setSize(width, heigth);
		window.setVisible(true);
	}
	
	public void Render() {

	}
}
