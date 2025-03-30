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
	       
	    }
}