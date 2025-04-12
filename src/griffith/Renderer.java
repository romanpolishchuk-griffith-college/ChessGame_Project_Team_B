package griffith;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.LineBorder;

import griffith.Game.GAME_STATE;

// Represents a renderer for the game.
public class Renderer {
	// The window of the game
	private JFrame window;
	// The board of the game
	private Board board;
	// The welcome panel of the game
	private JPanel welcomePanel;
	// Whether the game is drawn
	private boolean isGameDrawn = false;
	
	// Sets up the game.
	public void Setup(String title, int width, int heigth) {
		// Create a new window
		window = new JFrame(title);
		
		try {
			ImageIcon titleIcon = new ImageIcon(getClass().getResource("/res/titleIcon.png"));
		
			window.setIconImage(titleIcon.getImage());
			
			}catch(NullPointerException e){
				System.out.println("Failed to load title icon"+ e.getStackTrace());
			}
		
		
		// Set the size of the window
		window.setSize(width, heigth);
		// Set the default close operation of the window
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Create a new board
		board = new Board(true);
		window.setVisible(true);
	
        }
	// Renders the game.
	public void RenderGame() {
		// If the welcome panel is not null
		if (welcomePanel != null) {
			// Remove the welcome panel
			window.remove(welcomePanel);
			// Set the welcome panel to null
			welcomePanel = null;
		}
		// If the game is not drawn
		if(!isGameDrawn) {
			// Draw the board
			board.draw(window);
			// Create the captured panel
			createCapturedPanel();
			// Create the stats panel
			createStatsPanel();
			// Set the game as drawn
			isGameDrawn = true;
		}
	}
	// Renders the menu.
	public void RenderMenu() {
		// If the board is not null
		if (board != null) {
			// Remove the board
			window.remove(board);
		}
		// Create the welcome panel
		createWelcomePanel();
	}
	// Creates the welcome panel.
	private void createWelcomePanel() {
		// Create a new welcome panel
    	welcomePanel = new GradientPanel(); //New panel for the welcome screen
		// Set the layout of the welcome panel
        welcomePanel.setLayout(new GridBagLayout());//Using GridBagLayout for flexible
        
        ImageIcon chessImageForGameMenu = null;
       
        try {
    	   chessImageForGameMenu = new ImageIcon(getClass().getResource("/res/gameMenuChessImage.png"));
       }catch(Exception e){
			System.out.println("Failed to load title icon"+ e.getMessage());
			} 
		
       JLabel welcomeLabel;
       
       if (chessImageForGameMenu != null) {
    	    welcomeLabel = new JLabel("Chess", chessImageForGameMenu, JLabel.CENTER); // With icon
    	} else {
    	    welcomeLabel = new JLabel("Chess"); // Without icon
    	}
       
		// Set the font of the welcome label
        welcomeLabel.setFont(new Font("Roboto", Font.BOLD, 80));//Setting font for Title
        // Set white color of text
        welcomeLabel.setForeground(Color.WHITE);
        // Create a new start button
        JButton startButton = new JButton("Start Game");
		// Create a new exit button
        JButton exitButton = new JButton("Quit");
        
     
        startButton.setBackground(new Color(60, 179, 113));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setFont(new Font("Roboto", Font.BOLD, 16));
        startButton.setBorder(new LineBorder(Color.DARK_GRAY, 2));
        startButton.setPreferredSize(new Dimension(120,50));
        
        exitButton.setBackground(new Color(250, 106, 104));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(true);
        exitButton.setFont(new Font("Roboto", Font.BOLD, 16));
        exitButton.setBorder(new LineBorder(Color.DARK_GRAY, 2));
        exitButton.setPreferredSize(new Dimension(120,50));

		// Add an action listener to the start button
        startButton.addActionListener(e -> changeGameState());
		// Add an action listener to the exit button
        exitButton.addActionListener(e -> System.exit(0));
		// Create a new button panel
        JPanel buttonPanel = new JPanel();
        // Removing margin color
        buttonPanel.setOpaque(false);
		// Add the start button to the button panel
        buttonPanel.add(startButton); 
		// Add the exit button to the button panel
        buttonPanel.add(exitButton);  
		// Create a new grid bag constraint
        GridBagConstraints gbc = new GridBagConstraints();
		// Set the grid x of the grid bag constraint
        gbc.gridx = 0;
		// Set the grid y of the grid bag constraint
        gbc.gridy = 0;
		// Set the insets of the grid bag constraint
        gbc.insets = new Insets(10, 10, 10, 10);
		// Add the welcome label to the welcome panel
        welcomePanel.add(welcomeLabel, gbc);

        gbc.gridy = 1;
        welcomePanel.add(buttonPanel, gbc);
		// Add the welcome panel to the window
        window.add(welcomePanel, BorderLayout.CENTER);
		// Revalidate the window
        window.revalidate();
		// Repaint the window
        window.repaint();
    }
	// Creates the captured panel.
    private void createCapturedPanel() {
		// Create a new captured panel
        JPanel capturedPanel = new JPanel();  //Panel for captured pieces
		// Set the preferred size of the captured panel
        capturedPanel.setPreferredSize(new Dimension(150, 600));  //size of the panel
		// Set the border of the captured panel
        capturedPanel.setBorder(BorderFactory.createTitledBorder("Captured Pieces"));  
		// Add the captured panel to the window
        window.add(capturedPanel, BorderLayout.EAST); 
		// Revalidate the window
        window.revalidate();
		// Repaint the window
        window.repaint();
    }
	// Creates the stats panel.
    private void createStatsPanel() {
		// Create a new stats panel
        JPanel statsPanel = new JPanel(); 
		// Create a new status label
        JLabel statusLabel = new JLabel("Turn: White");  
		// Create a new move counter label
        JLabel moveCounterLabel = new JLabel("Moves: 0"); 
		// Add the status label to the stats panel
        statsPanel.add(statusLabel);  
		// Add the move counter label to the stats panel
        statsPanel.add(moveCounterLabel);
		// Add the stats panel to the window
        window.add(statsPanel, BorderLayout.SOUTH); 
		// Revalidate the window
        window.revalidate();
		// Repaint the window
        window.repaint();
    }
	
	private void changeGameState() {
		Game.setGameState(GAME_STATE.ACTIVE_GAME);
	}

}
