package griffith;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import griffith.Game.GAME_STATE;

public class Renderer {
	
	private JFrame window;
	private Board board;
	private JPanel welcomePanel;
	
	public void Setup(String title, int width, int heigth) {
		window = new JFrame(title);
		window.setSize(width, heigth);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		board = new Board(true);
		
		window.setVisible(true);
	}
	
	public void RenderGame() {
		if (welcomePanel != null) {
			window.remove(welcomePanel);
			welcomePanel = null;
		}
		
		window.add(board);
		window.revalidate();
		window.repaint();
	}
	
	public void RenderMenu() {
		if (board != null) {
			window.remove(board);
		}
		createWelcomePanel();
	}
	
	private void createWelcomePanel() {
    	welcomePanel = new JPanel(); //New panel for the welcome screen
        welcomePanel.setLayout(new GridBagLayout());//Using GridBagLayout for flexible
   
        JLabel welcomeLabel = new JLabel("Chess"); 
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));//Setting font for Title

        JButton startButton = new JButton("Start Game");
        JButton exitButton = new JButton("Quit");
        
        startButton.addActionListener(e -> changeGameState());
        exitButton.addActionListener(e -> System.exit(0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton); 
        buttonPanel.add(exitButton);  
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        welcomePanel.add(welcomeLabel, gbc);

        gbc.gridy = 1;
        welcomePanel.add(buttonPanel, gbc);
        
        window.add(welcomePanel, BorderLayout.CENTER);
        window.revalidate();
        window.repaint();
    }
	
	private void changeGameState() {
		Game.setGameState(GAME_STATE.ACTIVE_GAME);
	}

}
