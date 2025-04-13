package griffith;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Component;

import javax.swing.*;

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
    	welcomePanel = new JPanel(); //New panel for the welcome screen
		// Set the layout of the welcome panel
        welcomePanel.setLayout(new GridBagLayout());//Using GridBagLayout for flexible
		// Create a new welcome label
        JLabel welcomeLabel = new JLabel("Chess");
		// Set the font of the welcome label
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));//Setting font for Title
		// Create a new start button
        JButton startButton = new JButton("Start Game");
		// Create a new exit button
        JButton exitButton = new JButton("Quit");
		// Add an action listener to the start button
        startButton.addActionListener(e -> changeGameState());
		// Add an action listener to the exit button
        exitButton.addActionListener(e -> System.exit(0));
		// Create a new button panel
        JPanel buttonPanel = new JPanel();
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
		//Vertical box layout for the main panel
		capturedPanel.setLayout(new BoxLayout(capturedPanel, BoxLayout.Y_AXIS));
		// Set the preferred size of the captured panel
        capturedPanel.setPreferredSize(new Dimension(150, 600));  //size of the panel
		// Set the border of the captured panel
        capturedPanel.setBorder(BorderFactory.createTitledBorder("Captured Pieces"));  
		
		//Panel for white pieces 
		JPanel whiteCapturedPanel = new JPanel();
		//Left aligned flow layout
		whiteCapturedPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		//Adding a title to the panel
		whiteCapturedPanel.setBorder(BorderFactory.createTitledBorder("White pieces captured"));
		//Left align
		whiteCapturedPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Panel for black pieces
		JPanel blackCapturedPanel = new JPanel();
		//Left aligned flow layout
		blackCapturedPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		//Adding a title to the panel
		blackCapturedPanel.setBorder(BorderFactory.createTitledBorder("Black pieces captured"));
		//Left align
		blackCapturedPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		//Both panels will be in the main captured panel		
		capturedPanel.add(whiteCapturedPanel);
		capturedPanel.add(blackCapturedPanel);

		// Add the captured panel to the window
        window.add(capturedPanel, BorderLayout.EAST); 

		//Timer to update captured pieces display periodically
		new Timer(500, e -> updateCapturedPieces(whiteCapturedPanel, blackCapturedPanel)).start();
		
		// Revalidate the window
        window.revalidate();
		// Repaint the window
        window.repaint();
    }
	private void updateCapturedPieces(JPanel whiteCapturedPanel, JPanel blackCapturedPanel) {
		//Clear existing displays
		whiteCapturedPanel.removeAll();
		blackCapturedPanel.removeAll();
		
		//Loop for all captured white pieces
		for (ChessPiece piece : board.getCapturedWhitePieces()){
			 //Checks if piece has a valid image
			 if (piece.sprite != null) {
				//Creates a label with piece image
				JLabel pieceLabel = new JLabel(new ImageIcon(piece.sprite));
				
				//Size for the chess piece 
				pieceLabel.setPreferredSize(new Dimension(40, 40));
				
				//Add piece to white captured panel
				whiteCapturedPanel.add(pieceLabel);
			}
		}
		//Loop for all captured black pieces
		for (ChessPiece piece : board.getCapturedBlackPieces()) {
			//Checks if piece has a valid image
			if (piece.sprite != null) {
				//Creates a label with piece image
				JLabel pieceLabel = new JLabel(new ImageIcon(piece.sprite));
				
				//Size for the chess piece 
				pieceLabel.setPreferredSize(new Dimension(40, 40));
				
				//Add piece to black captured panel
				blackCapturedPanel.add(pieceLabel);
			}
		}
		//Refresh white captured panel layout
		whiteCapturedPanel.revalidate();
		//Redraw white captured panel
		whiteCapturedPanel.repaint();
		//Refresh black captured panel layout
		blackCapturedPanel.revalidate();
		//Redraw black captured panel
		blackCapturedPanel.repaint();
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
