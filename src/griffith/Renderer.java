package griffith;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.LineBorder;

import griffith.Game.GAME_STATE;

// Represents a renderer for the game.
public class Renderer {

    // The window of the game
    private JFrame window;

    // The board of the game
    private static Board board;

    // The welcome panel of the game
    private JPanel welcomePanel;

    // Whether the game is drawn
    private boolean isGameDrawn = false;

    public Timer countdownTimer; // Timer for countdown
    public int timeLeft = 300;   //Default time in seconds (5 minutes)
    private JLabel timerLabel;      //Timer label for countdown display
    public boolean isTimerEnabled = false; // Add a flag to track if the timer is enabled

    // Sets up the game.
    public void Setup(String title, int width, int heigth) {

        // Create a new window
        window = new JFrame(title);

        try {

            ImageIcon titleIcon = new ImageIcon(getClass().getResource("/res/titleIcon.png"));

            window.setIconImage(titleIcon.getImage());

        } catch (NullPointerException e) {
            System.out.println("Failed to load title icon" + e.getStackTrace());
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
        if (!isGameDrawn) {

            // Draw the board
            board.draw(window);

            // Create the captured panel
            createCapturedPanel();

            // Create the stats panel
            createStatsPanel();

            // Set the game as drawn
            isGameDrawn = true;
            
         // Get the computer move
            int[] moves = GameLogic.getComputerMove(board);

            if (moves != null) {
                if (board.isUnderCheck(!GameLogic.isPlayerWhite())){
                    ArrayList<Map<ChessPiece, String>> validMovesUnderCheck = board.getValidMovesUnderCheck(!GameLogic.isPlayerWhite());
                    ChessPiece defendingPiece = validMovesUnderCheck.get(0).keySet().iterator().next();
                    String defendingPieceMove = validMovesUnderCheck.get(0).get(defendingPiece);
                    moves[0] = defendingPiece.getX();
                    moves[1] = defendingPiece.getY();
                    moves[2] = Integer.parseInt(defendingPieceMove.charAt(0) + "");
                    moves[3] = Integer.parseInt(defendingPieceMove.charAt(2) + "");
                }

                ChessPiece pieceMove = board.getPiece(moves[0], moves[1]);
                board.movePiece(pieceMove, moves[2], moves[3]);
                pieceMove.button.setLocation(moves[2] * board.getSquareSize(), (board.getBoredSize() - 1 - moves[3]) * board.getSquareSize());
                System.out.println(moves[0] + " " + moves[1] + " " + moves[2] + " " + moves[3]);

                if (GameLogic.isPlayerWhite()){
                    if(board.isBlackWon()){
                        JOptionPane.showMessageDialog(window, "You have lose :(.");
                        return;
                    }
                }
                else {
                    if(board.isWhiteWon()){
                        JOptionPane.showMessageDialog(window, "You have lose :(.");
                        return;
                    }
                }
            }
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

        } catch (Exception e) {
            System.out.println("Failed to load game Menu Chess Image icon" + e.getMessage());
        }

        JLabel welcomeLabel;

        // Depending on loading custom title icon create game with it or with default
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

        // Setting new button 'Start game' design
        startButton.setBackground(new Color(60, 179, 113));            // green
        startButton.setForeground(Color.WHITE);                                // white
        startButton.setFocusPainted(false);
        startButton.setFont(new Font("Roboto", Font.BOLD, 16));     // New font
        startButton.setBorder(new LineBorder(Color.DARK_GRAY, 2));     // border color
        startButton.setPreferredSize(new Dimension(120, 50));      // button size

        // Setting new button 'Quit' design
        exitButton.setBackground(new Color(250, 106, 104)); // red
        exitButton.setForeground(Color.WHITE);                                // white
        exitButton.setFocusPainted(true);
        exitButton.setFont(new Font("Roboto", Font.BOLD, 16));    // New font
        exitButton.setBorder(new LineBorder(Color.DARK_GRAY, 2));    // border color
        exitButton.setPreferredSize(new Dimension(120, 50));    // button size


        // Add an action listener to the start button
        startButton.addActionListener(e -> showColorSelectionDialog());

        // Add an action listener to the exit button
        exitButton.addActionListener(e -> System.exit(0));

        // Create a new button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);  // Removing margin color

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
    public void createCapturedPanel() {

        // Create a new captured panel

        JPanel capturedPanel = new JPanel();  //Panel for captured pieces

        //Vertical box layout for the main panel
        capturedPanel.setLayout(new BoxLayout(capturedPanel, BoxLayout.Y_AXIS));

        // Set the preferred size of the captured panel
        capturedPanel.setPreferredSize(new Dimension(350, 600));  //size of the panel

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

	public void updateCapturedPieces(JPanel whiteCapturedPanel, JPanel blackCapturedPanel) {
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
				pieceLabel.setPreferredSize(new Dimension(70, 70));
				
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
				pieceLabel.setPreferredSize(new Dimension(70, 70));
				
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

        if (isTimerEnabled) { // Only add the timer label if the timer is enabled
            timerLabel = new JLabel("Time Left: 05:00");
            statsPanel.add(timerLabel);
        }

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

    private void showColorSelectionDialog() {
        // Create a dialog for color selection
        String[] options = {"White", "Black"};
        int choice = JOptionPane.showOptionDialog(
            window,
            "Choose your color:",
            "Color Selection",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );
    
        //Handle the player's choice
        if (choice == 0) {
            System.out.println("Player chose White.");
            GameLogic.setPlayerColor(true); // Player is white
        } else if (choice == 1) {
            System.out.println("Player chose Black.");
            GameLogic.setPlayerColor(false); // Player is black
        }

        // Ask if the player wants a countdown timer
        int timerChoice = JOptionPane.showConfirmDialog(
            window,
            "Do you want to play with a countdown timer?",
            "Countdown Timer",
            JOptionPane.YES_NO_OPTION
        );

        if (timerChoice == JOptionPane.YES_OPTION) {
            startCountdownTimer();
        }


        // Change the game state to start the game
        changeGameState();
    }
    // Add a method to start the countdown timer
    public void startCountdownTimer() {
        isTimerEnabled = true; // Set the timer flag to true
        countdownTimer = new Timer(1000, e -> {
            timeLeft--;
            updateTimerDisplay();
            if (timeLeft <= 0) {
                countdownTimer.stop();
                JOptionPane.showMessageDialog(window, "Time's up! Game over. No winner.");
                System.exit(0); // End the game
            }
        });
        countdownTimer.start();
    }

    // Add a method to update the timer display
    private void updateTimerDisplay() {
        if (isTimerEnabled && timerLabel != null) { // Update the timer only if it's enabled
            timerLabel.setText("Time Left: " + formatTime(timeLeft));
        }
    }
    
    // Add a helper method to format time
    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d", minutes, secs);
    }

    
    public static Board getGameBoard() {
    	return board;
    }
}
