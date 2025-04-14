package griffith;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GradientPanel extends JPanel {

	 @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Graphics2D g2d = (Graphics2D) g;

	        // Gradient from top to bottom
	        GradientPaint gp = new GradientPaint(
	            0, 0, new Color(255, 94, 98),   
	            0, getHeight(), new Color(255, 195, 113) 
	        );

	        g2d.setPaint(gp);
	        g2d.fillRect(0, 0, getWidth(), getHeight());
	    }
}
