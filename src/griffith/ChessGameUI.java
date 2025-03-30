//package griffith;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class ChessGameUI {
//	    private JFrame frame;  //main window of the game
//	    public static JPanel boardPanel, menuPanel, capturedPanel, statsPanel, welcomePanel; //different panels for different sections
//	    private JLabel statusLabel, moveCounterLabel; //game information
//	    private int moveCounter = 0;  //cOunts the number of moves made in the game
//
////	    public ChessGameUI() {
////	        initializeUI();//Initializes the graphical interface 
////	    }
////	   
//	    public static void createWelcomePanel() {
//	    	welcomePanel = new JPanel(); //New panel for the welcome screen
//	        welcomePanel.setLayout(new GridBagLayout());//Using GridBagLayout for flexible positioning
//	   
//	        JLabel welcomeLabel = new JLabel("Chess"); 
//	        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));//Setting font for Title
//
//	        JButton startButton = new JButton("Start Game"); //Button to start the game
//	        JButton exitButton = new JButton("Quit");  //Button to quit the game
//	        
//	        startButton.addActionListener(e -> startGame( window));  //Calls startGame() to start the game
//	        exitButton.addActionListener(e -> System.exit(0)); //Exits application if pressed
//
//	        JPanel buttonPanel = new JPanel();//Panel to keep the buttons
//	        buttonPanel.add(startButton); 
//	        buttonPanel.add(exitButton);  
//	        
//	        GridBagConstraints gbc = new GridBagConstraints(); //Setting layout constraints for the components
//	        gbc.gridx = 0;  //Position for the welcome label
//	        gbc.gridy = 0;
//	        gbc.insets = new Insets(10, 10, 10, 10);  //Adding padding around the components
//	        welcomePanel.add(welcomeLabel, gbc);  //Adding a welcome label 
//
//	        gbc.gridy = 1;  //Sets the position for the button panel
//	        welcomePanel.add(buttonPanel, gbc);//Adding the button panel to the welcome screen
//	        frame.add(welcomePanel, BorderLayout.CENTER); //Center aligning the welcome panel
//	        
//	    }
//	    
//	    public static void startGame( JFrame frame) {
//	        frame.remove(welcomePanel); //Removes the welcome panel when game starts
//	        createGameUI(); //Creates the game interface
//	        frame.revalidate(); //Revalidates the frame to reflect the changes
//	        frame.repaint();  //Redraws the frame to show the updated UI
//	    }
//
//	    private void createGameUI() {
//	        createMenuPanel();  //Menu panel
//	        createBoardPanel();  //Chess board
//	        createCapturedPanel(); //Panel to display captured pieces
//	        createStatsPanel();  //Panel to display game statistics(like who's turn it is)
//	    }
//	    private void createMenuPanel() {
//	    	menuPanel = new JPanel();  //Creates a new panel for the menu
//	        JButton restartButton = new JButton("Restart");  //Button to restart the game
//	        JButton exitButton = new JButton("Quit Game");  // Button to quit the game
//
//	        restartButton.addActionListener(e -> resetGame());  
//	        exitButton.addActionListener(e -> System.exit(0));  
//
//	        menuPanel.add(restartButton);  
//	        menuPanel.add(exitButton);  
//	        frame.add(menuPanel, BorderLayout.NORTH); //Adds the menu panel to the top of the window
//	    }
//	    
//	    private void createBoardPanel() {
//	        boardPanel = new JPanel(new GridLayout(8, 8)); //8x8 grid layout
//	        for (int row = 0; row < 8; row++) {
//	            for (int col = 0; col < 8; col++) {
//	                JButton square = new JButton();  //Creates a new button for each square on the board
//	                square.setOpaque(true);  //Making the button opaque so we can set a background color
//	                square.setBorderPainted(false); 
//
//	                //Setting the background color to white or black 
//	                if ((row + col) % 2 == 0) {
//	                    square.setBackground(Color.WHITE); 
//	                } else {
//	                    square.setBackground(Color.BLACK);  
//	                }
//	                
//	                square.setPreferredSize(new Dimension(50, 50));  //Size of each square
//	                boardPanel.add(square); 
//	            }
//	        }
//	        frame.add(boardPanel, BorderLayout.CENTER); 
//	    }
//	    
//	    private void createCapturedPanel() {
//	        capturedPanel = new JPanel();  //Panel for captured pieces
//	        capturedPanel.setPreferredSize(new Dimension(150, 600));  //size of the panel
//	        capturedPanel.setBorder(BorderFactory.createTitledBorder("Captured Pieces"));  
//	        frame.add(capturedPanel, BorderLayout.EAST); 
//	    }
//	    
//	    private void createStatsPanel() {
//	        statsPanel = new JPanel();  //Panel for game stats
//	        statusLabel = new JLabel("Turn: White");  //Label to show whose turn it is
//	        moveCounterLabel = new JLabel("Moves: 0"); //Shows total number of moves made 
//	        statsPanel.add(statusLabel);  
//	        statsPanel.add(moveCounterLabel);
//	        frame.add(statsPanel, BorderLayout.SOUTH); 
//	    }
//
//	    private void resetGame() {
//	        moveCounter = 0;  //Resets the move counter
//	        statusLabel.setText("Turn: White");  //Sets turn to White
//	        moveCounterLabel.setText("Moves: 0");  //Move counter displays 0
//	        capturedPanel.removeAll();  //Remove all captured pieces
//	        capturedPanel.revalidate();  //Revalidate the captured panel
//	        capturedPanel.repaint();  //Repaint the captured panel to reflect the changes
//	    }
//	    public static void main(String[] args) {
//	        SwingUtilities.invokeLater(ChessGameUI::new); //Ensures the UI is created and updated
//	    }
//	    
//}