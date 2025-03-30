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
	    }
	    
	    private void startGame() {
	      
	    }

}