package griffith;

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
		if(windowWidth <= 0) {
			//Default value if value is invalid
			windowWidth = 1000;
		}
		// If the window height is less than or equal to 0, set the window height to 1000
		if(windowHeight <= 0) {
			//Default value if value is invalid
			windowHeight = 1000;
		}
		// If the window title is null or empty, set the window title to "Title"
		if(windowTitle == null || windowTitle.equals("")) {
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
	// Runs the game
	public void Run() {
		Renderer renderer = new Renderer();
		
		renderer.Setup(windowTitle, windowWidth, windowHeight);
		
		// The last state of the game
		GAME_STATE lastState = null;
		
		// Run the game
		while(gameActive) {
			// If the state of the game has changed, render the game
			if(gameState != lastState) {  // Only render when state changes
				// If the game is in the menu state, render the menu
				if(gameState == GAME_STATE.MENU) {
					renderer.RenderMenu();
				}
				else if (gameState == GAME_STATE.ACTIVE_GAME) {
					renderer.RenderGame();
				}
				lastState = gameState;
			}
			
			 try {
			 	Thread.sleep(16);  // Add a small delay to prevent CPU overuse
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
}