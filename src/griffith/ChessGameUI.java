package griffith;

import javax.swing.*;
import java.awt.*;

public class ChessGameUI {
	    private JFrame frame;  //main window of the game
	    private JPanel boardPanel, menuPanel, capturedPanel, statsPanel, welcomePanel; //different panels for different sections
	    private JLabel statusLabel, moveCounterLabel; //game information
	    private int moveCounter = 0;  //cOunts the number of moves made in the game

	    public ChessGameUI() {
	        initializeUI();//Initializes the graphical interface 
	    }
	    private void initializeUI() {
	    	 frame = new JFrame("Chess");//JFrame with title "Chess"
	         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Exits the application when window is closed
	         frame.setSize(800, 600);//Window dimensions
	         frame.setLayout(new BorderLayout());//Using BorderLayout to organize panels
	         
	         createWelcomePanel();  //Displays the welcome screen first
	         frame.setVisible(true);
	    }
	    
	    private void createWelcomePanel() {
	    	welcomePanel = new JPanel(); //New panel for the welcome screen
	        welcomePanel.setLayout(new GridBagLayout());//Using GridBagLayout for flexible positioning
	   
	        JLabel welcomeLabel = new JLabel("Chess"); 
	        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));//Setting font for Title

	        JButton startButton = new JButton("Start Game"); //Button to start the game
	        JButton exitButton = new JButton("Quit");  //Button to quit the game
	        
	        startButton.addActionListener(e -> startGame());  //Calls startGame() to start the game
	        exitButton.addActionListener(e -> System.exit(0)); //Exits application if pressed

	        JPanel buttonPanel = new JPanel();//Panel to keep the buttons
	        buttonPanel.add(startButton); 
	        buttonPanel.add(exitButton);  
	        
	        GridBagConstraints gbc = new GridBagConstraints(); //Setting layout constraints for the components
	        gbc.gridx = 0;  //Position for the welcome label
	        gbc.gridy = 0;
	        gbc.insets = new Insets(10, 10, 10, 10);  //Adding padding around the components
	        welcomePanel.add(welcomeLabel, gbc);  //Adding a welcome label 

	        gbc.gridy = 1;  //Sets the position for the button panel
	        welcomePanel.add(buttonPanel, gbc);//Adding the button panel to the welcome screen
	        frame.add(welcomePanel, BorderLayout.CENTER); //Center aligning the welcome panel
	        
	    }
	    
	    private void startGame() {
	        frame.remove(welcomePanel); //Removes the welcome panel when game starts
	        createGameUI(); //Creates the game interface
	        frame.revalidate(); //Revalidates the frame to reflect the changes
	        frame.repaint();  //Redraws the frame to show the updated UI
	    }

	    private void createGameUI() {
	    
	    }


}