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

    // The title of the window
    private String windowTitle;

    // The width of the window
    private int windowWidth;

    // The height of the window
    private int windowHeight;

    // Whether the game is active
    private boolean gameActive = true;

    enum GAME_STATE {
        MENU,
        ACTIVE_GAME
    }

    // The current state of the game
    private static GAME_STATE gameState = GAME_STATE.MENU;

    // Constructor with specified window title, width, and height.
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

    // Sets the current state of the game
    public static void setGameState(GAME_STATE state) {
        gameState = state;
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
                	//Path to the background music
                	String soundPath = "src/res/background-music.wav";
					try {
						//Get the audio stream
						AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundPath).getAbsoluteFile());
                    	//Create clip out of audio stream
						Clip clip = AudioSystem.getClip();
                    	clip.open(audioInputStream);
                    	//Play the clip
                    	clip.start();
					} catch (UnsupportedAudioFileException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (LineUnavailableException e1) {
						e1.printStackTrace();
					}
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

    // Returns the window title
    public String getWindowTitle() {
        return windowTitle;
    }

    // Sets the window title
    public void setWindowTitle(String windowTitle) {
        this.windowTitle = windowTitle;
    }

    // Returns the window width
    public int getWindowWidth() {
        return windowWidth;
    }

    // Sets the window width
    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    // Returns the window height
    public int getWindowHeight() {
        return windowHeight;
    }

    // Sets the window height
    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    // Add a method to reset the timer
    public void resetTimer(Renderer renderer) {
        renderer.timeLeft = 300; // Reset to 5 minutes
        if (renderer.countdownTimer != null) {
            renderer.countdownTimer.stop();
        }
    }
}