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
	
	public void Setup(String title, int width, int heigth) {
		window = new JFrame(title);
		window.setSize(width, heigth);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
	
		window.setVisible(true);
	}
	
	public void RenderGame() {
		board = new Board(true);
		window.add(board);
		board.repaint();
		System.out.println("Rendering frame...");
	}
	
	public void RenderMenu() {
		createWelcomePanel();
		
	}
	
	private void createWelcomePanel() {
    	JPanel welcomePanel = new JPanel(); //New panel for the welcome screen
        welcomePanel.setLayout(new GridBagLayout());//Using GridBagLayout for flexible positioning
   
        JLabel welcomeLabel = new JLabel("Chess"); 
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));//Setting font for Title

        JButton startButton = new JButton("Start Game"); //Button to start the game
        JButton exitButton = new JButton("Quit");  //Button to quit the game
        
        startButton.addActionListener(e -> changeGameState(welcomePanel));  //Calls startGame() to start the game
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
        window.add(welcomePanel, BorderLayout.CENTER); //Center aligning the welcome panel
        
        welcomePanel.revalidate();  //Revalidates the frame to reflect the changes
        welcomePanel.repaint();     //Redraws the frame to show the updated UI
    }
	private void changeGameState(JPanel panel) {
		Game.setGameState(GAME_STATE.ACTIVE_GAME);
		panel.remove(panel); //Removes the welcome panel when game starts
		panel.revalidate();  //Revalidates the frame to reflect the changes
		panel.repaint();     //Redraws the frame to show the updated UI
	}

}
