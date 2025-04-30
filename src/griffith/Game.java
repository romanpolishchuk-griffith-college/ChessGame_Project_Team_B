package griffith;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

// Represents a game.
public class Game {

    // Window parameters
    private String windowTitle;
    private int windowWidth;
    private int windowHeight;

    private boolean gameActive = true;

    enum GAME_STATE {
        MENU,
        ACTIVE_GAME
    }

    // The current state of the game
    private static GAME_STATE gameState = GAME_STATE.MENU;

    // Constructor
    public Game(String windowTitle, int windowWidth, int windowHeight) {

        // If the window width is less than or equal to 0, set the window width to 1000
        if (windowWidth <= 0) {
            //Default value if value is invalid
            windowWidth = 1000;
        }

        // If the window height is less than or equal to 0, set the window height to 1000
        if (windowHeight <= 0) {
            //Default value if value is invalid
            windowHeight = 1000;
        }

        // If the window title is null or empty, set the window title to "Title"
        if (windowTitle == null || windowTitle.equals("")) {
            //Default value if value is invalid
            windowTitle = "Title";
        }

        // Set the window title
        this.windowTitle = windowTitle;

        // Set the window width
        this.windowWidth = windowWidth;

        // Set the window height
        this.windowHeight = windowHeight;
    }

    // Returns the current state of the game
    public static GAME_STATE getGameState() {
        return gameState;
    }

    // Ensure the game state is set to ACTIVE_GAME when starting the game
    public void Run() {
        //creating a new renderer variable
        Renderer renderer = new Renderer();
        renderer.Setup(windowTitle, windowWidth, windowHeight);

        GAME_STATE lastState = null;

        //making sure the timer starts
        while (gameActive) {
            if (gameState != lastState) {
                if (gameState == GAME_STATE.MENU) {
                    renderer.RenderMenu();
                } else if (gameState == GAME_STATE.ACTIVE_GAME) {
                	
                	//Play background music
                	SoundControl.playBackroundMusic();
                	
                    renderer.RenderGame(); // This will start the timer
                }
                lastState = gameState;
            }

            try {
                Thread.sleep(16); // Prevent CPU overuse
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Add a method to reset the timer
    public void resetTimer(Renderer renderer) {
        renderer.timeLeft = 300; // Reset to 5 minutes
        if (renderer.countdownTimer != null) {
            renderer.countdownTimer.stop();
        }
    }


    // Getters
    public int getWindowHeight() {
        return windowHeight;
    }

    public String getWindowTitle() {
        return windowTitle;
    }

    public int getWindowWidth() {
        return windowWidth;
    }


    //Setters
    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    public void setWindowTitle(String windowTitle) {
        this.windowTitle = windowTitle;
    }

    public static void setGameState(GAME_STATE state) {
        gameState = state;
    }
}